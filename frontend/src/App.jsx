import React from 'react';
import { Activity, BarChart3, Dumbbell, HeartPulse, Salad, Scale, Target, UserRound } from 'lucide-react';
import { calculateImc, createAssessment, getDashboard, registerUser } from './api.js';

const logoPath = '/dream-fit-logo.png';

const initialUser = {
  nome: '',
  email: '',
  senha: '',
  idade: '',
  sexo: '',
  peso: '',
  altura: '',
  metaPeso: '',
  objetivo: 'emagrecer',
  nivelAtividade: 'moderado',
  experienciaComTreino: 'iniciante',
  restricoes: 'sem restricoes',
};

const steps = [
  { key: 'nome', label: 'Qual e o seu nome?', type: 'text', placeholder: 'Ana Silva' },
  { key: 'email', label: 'Qual e o seu email?', type: 'email', placeholder: 'ana@email.com' },
  { key: 'senha', label: 'Crie uma senha', type: 'password', placeholder: 'Minimo 6 caracteres' },
  { key: 'idade', label: 'Qual e a sua idade?', type: 'number', placeholder: '28' },
  { key: 'sexo', label: 'Qual e o seu sexo?', type: 'select', options: ['feminino', 'masculino', 'outro'] },
  { key: 'peso', label: 'Qual e o seu peso atual?', type: 'number', placeholder: '72' },
  { key: 'altura', label: 'Qual e a sua altura em metros?', type: 'number', placeholder: '1.70', step: '0.01' },
  { key: 'metaPeso', label: 'Qual e a sua meta de peso?', type: 'number', placeholder: '65' },
  { key: 'objetivo', label: 'Qual e o seu objetivo?', type: 'select', options: ['emagrecer', 'ganhar massa', 'manter peso'] },
  { key: 'nivelAtividade', label: 'Qual e o seu nivel de atividade?', type: 'select', options: ['sedentario', 'leve', 'moderado', 'intenso'] },
  { key: 'experienciaComTreino', label: 'Qual e sua experiencia com treino?', type: 'select', options: ['iniciante', 'intermediario', 'avancado'] },
  { key: 'restricoes', label: 'Tem restricoes ou lesoes?', type: 'text', placeholder: 'Ex: joelho, lactose, sem restricoes' },
];

const assessmentFields = [
  { key: 'peso', label: 'Peso', placeholder: 'kg' },
  { key: 'altura', label: 'Altura', placeholder: 'm ou cm' },
  { key: 'idade', label: 'Idade', placeholder: 'anos' },
  { key: 'sexo', label: 'Sexo', type: 'select', options: ['feminino', 'masculino', 'outro'] },
  { key: 'pescoco', label: 'Pescoco', placeholder: 'cm' },
  { key: 'ombro', label: 'Ombro', placeholder: 'cm' },
  { key: 'torax', label: 'Torax', placeholder: 'cm' },
  { key: 'cintura', label: 'Cintura', placeholder: 'cm' },
  { key: 'quadril', label: 'Quadril', placeholder: 'cm' },
  { key: 'abdomen', label: 'Abdomen', placeholder: 'cm' },
  { key: 'bracoDireito', label: 'Braco direito', placeholder: 'cm' },
  { key: 'bracoEsquerdo', label: 'Braco esquerdo', placeholder: 'cm' },
  { key: 'antebracoDireito', label: 'Antebraco direito', placeholder: 'cm' },
  { key: 'antebracoEsquerdo', label: 'Antebraco esquerdo', placeholder: 'cm' },
  { key: 'coxaDireita', label: 'Coxa direita', placeholder: 'cm' },
  { key: 'coxaEsquerda', label: 'Coxa esquerda', placeholder: 'cm' },
  { key: 'panturrilhaDireita', label: 'Panturrilha direita', placeholder: 'cm' },
  { key: 'panturrilhaEsquerda', label: 'Panturrilha esquerda', placeholder: 'cm' },
  { key: 'dobraBicipital', label: 'Dobra bicipital', placeholder: 'mm' },
  { key: 'dobraTricipital', label: 'Dobra tricipital', placeholder: 'mm' },
  { key: 'dobraPeitoral', label: 'Dobra peitoral', placeholder: 'mm' },
  { key: 'dobraSubescapular', label: 'Dobra subescapular', placeholder: 'mm' },
  { key: 'dobraAxilarMedia', label: 'Dobra axilar media', placeholder: 'mm' },
  { key: 'dobraSuprailiaca', label: 'Dobra suprailiaca', placeholder: 'mm' },
  { key: 'dobraAbdominal', label: 'Dobra abdominal', placeholder: 'mm' },
  { key: 'dobraCoxa', label: 'Dobra coxa', placeholder: 'mm' },
  { key: 'dobraPanturrilha', label: 'Dobra panturrilha', placeholder: 'mm' },
];

const navItems = [
  { id: 'dashboard', label: 'Dashboard', icon: BarChart3 },
  { id: 'avaliacao', label: 'Avaliacao Fisica', icon: HeartPulse },
  { id: 'treino', label: 'Treino', icon: Dumbbell },
  { id: 'dieta', label: 'Dieta', icon: Salad },
  { id: 'evolucao', label: 'Evolucao', icon: Activity },
];

function formatValue(value, suffix = '') {
  if (value === undefined || value === null || value === '') return '--';
  return `${value}${suffix}`;
}

function PublicHome({ onStart }) {
  const [weight, setWeight] = React.useState('');
  const [height, setHeight] = React.useState('');
  const [age, setAge] = React.useState('');
  const [sex, setSex] = React.useState('feminino');
  const [imcResult, setImcResult] = React.useState(null);

  async function handleCalculate() {
    if (!weight || !height) return;
    const normalizedHeight = Number(height) > 3 ? Number(height) / 100 : Number(height);
    try {
      setImcResult(await calculateImc({ peso: weight, altura: normalizedHeight }));
    } catch {
      const imc = Number(weight) / (normalizedHeight ** 2);
      const rounded = Math.round(imc * 10) / 10;
      const classificacao = rounded < 18.5 ? 'Abaixo do peso' : rounded < 25 ? 'Saudavel' : rounded < 30 ? 'Sobrepeso' : 'Obesidade';
      setImcResult({ imc: rounded, classificacao });
    }
  }

  return (
    <main className="public-page">
      <header className="public-header">
        <div className="header-brand">
          <img src={logoPath} alt="Dream Fit" />
          <div>
            <strong>Dream Fit</strong>
            <span>Fitness & Performance</span>
          </div>
        </div>
        <button className="login-button" onClick={onStart}>Login</button>
      </header>

      <section className="home-hero">
        <div className="home-copy">
          <span className="pill">Seu controle fitness comeca aqui</span>
          <h1>Acompanhe seu corpo, calcule seu IMC e evolua com mais estrategia</h1>
          <p>A Dream Fit e uma plataforma fitness criada para centralizar dados importantes da sua evolucao fisica em um ambiente moderno, intuitivo e pratico.</p>
          <div className="hero-actions">
            <a className="primary-button compact" href="#calculadora-imc">Calcular IMC</a>
            <button className="ghost-button" onClick={onStart}>Entrar na plataforma</button>
          </div>
        </div>

        <div className="feature-panel">
          <span className="panel-label with-dot">Painel Dream Fit</span>
          <div className="feature-grid">
            <article><strong>IMC</strong><span>Monitoramento rapido</span></article>
            <article><strong>Treino</strong><span>Plano por objetivo</span></article>
            <article><strong>Dieta</strong><span>Baseada no perfil</span></article>
            <article><strong>Progresso</strong><span>Acompanhamento visual</span></article>
          </div>
        </div>
      </section>

      <section className="home-calculator" id="calculadora-imc">
        <div className="calculator-heading">
          <span className="pill small">Calculadora Inteligente</span>
          <h2>Calcule seu IMC</h2>
          <p>Insira seus dados e veja rapidamente sua classificacao corporal.</p>
        </div>

        <div className="calculator-card">
          <div className="calculator-form">
            <label>Idade<input value={age} onChange={(event) => setAge(event.target.value)} type="number" placeholder="Ex: 23" /></label>
            <label>Altura (cm)<input value={height} onChange={(event) => setHeight(event.target.value)} type="number" placeholder="Ex: 170" /></label>
            <div className="segmented-field">
              <span>Sexo</span>
              <div className="segmented-control">
                {['feminino', 'masculino'].map((option) => (
                  <button key={option} className={sex === option ? 'selected' : ''} type="button" onClick={() => setSex(option)}>{option}</button>
                ))}
              </div>
            </div>
            <label>Peso (kg)<input value={weight} onChange={(event) => setWeight(event.target.value)} type="number" placeholder="Ex: 68" /></label>
            <button className="primary-button calculator-button" onClick={handleCalculate}>Calcular agora</button>
          </div>

          <div className="calculator-result">
            <div className="imc-ring">
              <span>IMC</span>
              <strong>{imcResult ? imcResult.imc : '0.0'}</strong>
            </div>
            <div className="result-copy">
              <div className="dash-line" />
              <p>Use esse resultado como base inicial. Depois do login, a plataforma podera recomendar informacoes mais completas de treino e dieta.</p>
              <div className="classification-grid">
                {['Abaixo do peso', 'Saudavel', 'Sobrepeso', 'Obesidade'].map((item) => (
                  <span className={imcResult?.classificacao === item ? 'active' : ''} key={item}>{item}</span>
                ))}
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  );
}

function RegisterFlow({ onComplete }) {
  const [form, setForm] = React.useState(initialUser);
  const [stepIndex, setStepIndex] = React.useState(0);
  const [error, setError] = React.useState('');
  const [loading, setLoading] = React.useState(false);
  const step = steps[stepIndex];
  const value = form[step.key];
  const canAdvance = String(value).trim().length > 0 && !loading;

  async function next() {
    if (!canAdvance) return;
    if (stepIndex < steps.length - 1) {
      setStepIndex((current) => current + 1);
      return;
    }

    setLoading(true);
    setError('');
    try {
      await onComplete(form);
    } catch {
      setError('Nao foi possivel cadastrar. Verifique se o backend esta rodando.');
      setLoading(false);
    }
  }

  return (
    <main className="register-page">
      <section className="register-card">
        <img className="form-logo" src={logoPath} alt="Dream Fit" />
        <div className="progress">
          <span>Passo {stepIndex + 1} de {steps.length}</span>
          <div><span style={{ width: `${((stepIndex + 1) / steps.length) * 100}%` }} /></div>
        </div>
        <h1>{step.label}</h1>
        {step.type === 'select' ? (
          <select value={value} onChange={(event) => setForm({ ...form, [step.key]: event.target.value })}>
            <option value="">Selecione</option>
            {step.options.map((option) => <option key={option} value={option}>{option}</option>)}
          </select>
        ) : (
          <input value={value} onChange={(event) => setForm({ ...form, [step.key]: event.target.value })} type={step.type} step={step.step} placeholder={step.placeholder} autoFocus />
        )}
        {error && <p className="status-message error">{error}</p>}
        <div className="register-actions">
          <button disabled={stepIndex === 0 || loading} onClick={() => setStepIndex((current) => current - 1)}>Voltar</button>
          <button className="primary-button" disabled={!canAdvance} onClick={next}>{stepIndex === steps.length - 1 ? 'Entrar no dashboard' : 'Continuar'}</button>
        </div>
      </section>
    </main>
  );
}

function Sidebar({ active, onChange, dashboard }) {
  return (
    <aside className="sidebar">
      <div className="brand"><img src={logoPath} alt="Dream Fit" /></div>
      <div className="profile-mini"><UserRound size={20} /><span>{dashboard.nome}</span></div>
      <nav>
        {navItems.map((item) => {
          const Icon = item.icon;
          return <button key={item.id} className={active === item.id ? 'active' : ''} onClick={() => onChange(item.id)}><Icon size={18} />{item.label}</button>;
        })}
      </nav>
    </aside>
  );
}

function DashboardHome({ dashboard }) {
  const cards = [
    { title: 'IMC atual', value: dashboard.imc, note: 'Calculado no backend', icon: HeartPulse },
    { title: 'Classificacao', value: dashboard.classificacaoImc, note: dashboard.ultimaAvaliacao ? dashboard.ultimaAvaliacao.classificacaoCorporal : 'Sem avaliacao fisica completa', icon: Scale },
    { title: 'Objetivo', value: dashboard.objetivo, note: dashboard.metaSemanalSugerida, icon: Target },
    { title: 'Meta de peso', value: `${dashboard.metaPeso} kg`, note: `Atual: ${dashboard.pesoAtual} kg`, icon: BarChart3 },
    { title: 'Treino recomendado', value: 'Plano ativo', note: dashboard.treinoRecomendado, icon: Dumbbell },
    { title: 'Dieta recomendada', value: 'Estrategia ativa', note: dashboard.dietaRecomendada, icon: Salad },
    { title: 'Experiencia e restricoes', value: dashboard.experienciaComTreino, note: dashboard.restricoes, icon: UserRound },
  ];

  return (
    <section>
      <div className="section-heading"><span>Ola, {dashboard.nome}</span><h1>Dashboard fitness</h1></div>
      <div className="card-grid">
        {cards.map((card) => {
          const Icon = card.icon;
          return <article className="metric-card" key={card.title}><Icon size={22} /><span>{card.title}</span><strong>{card.value}</strong><p>{card.note}</p></article>;
        })}
      </div>
    </section>
  );
}

function PhysicalAssessment({ dashboard, onSaved }) {
  const [assessment, setAssessment] = React.useState({
    peso: dashboard.pesoAtual,
    altura: dashboard.altura,
    idade: dashboard.idade,
    sexo: dashboard.sexo,
    pescoco: '',
    ombro: '',
    torax: '',
    cintura: '',
    quadril: '',
    abdomen: '',
    bracoDireito: '',
    bracoEsquerdo: '',
    antebracoDireito: '',
    antebracoEsquerdo: '',
    coxaDireita: '',
    coxaEsquerda: '',
    panturrilhaDireita: '',
    panturrilhaEsquerda: '',
    dobraBicipital: '',
    dobraTricipital: '',
    dobraPeitoral: '',
    dobraSubescapular: '',
    dobraAxilarMedia: '',
    dobraSuprailiaca: '',
    dobraAbdominal: '',
    dobraCoxa: '',
    dobraPanturrilha: '',
  });
  const [result, setResult] = React.useState(dashboard.ultimaAvaliacao);
  const [error, setError] = React.useState('');
  const [loading, setLoading] = React.useState(false);

  async function handleSubmit(event) {
    event.preventDefault();
    setLoading(true);
    setError('');
    try {
      const saved = await createAssessment(dashboard.usuarioId, assessment);
      setResult(saved);
      await onSaved();
    } catch {
      setError('Nao foi possivel calcular a avaliacao. Verifique os dados e o backend.');
    } finally {
      setLoading(false);
    }
  }

  return (
    <section>
      <div className="section-heading">
        <span>Opcional e refinada</span>
        <h1>Avaliacao Fisica</h1>
      </div>
      <form className="form-grid assessment-form" onSubmit={handleSubmit}>
        {assessmentFields.map((field) => (
          <label key={field.key}>
            {field.label}
            {field.type === 'select' ? (
              <select value={assessment[field.key]} onChange={(event) => setAssessment({ ...assessment, [field.key]: event.target.value })}>
                {field.options.map((option) => <option key={option} value={option}>{option}</option>)}
              </select>
            ) : (
              <input type="number" step="0.01" value={assessment[field.key]} onChange={(event) => setAssessment({ ...assessment, [field.key]: event.target.value })} placeholder={field.placeholder} />
            )}
          </label>
        ))}
        <div className="form-actions">
          <button className="primary-button" disabled={loading} type="submit">Calcular avaliacao</button>
        </div>
      </form>
      {error && <p className="status-message error">{error}</p>}
      {result && (
        <div className="assessment-report">
          <div className="assessment-results">
            <article className="metric-card"><HeartPulse size={22} /><span>Percentual de gordura</span><strong>{formatValue(result.percentualGordura, '%')}</strong><p>{result.classificacaoCorporal}</p></article>
            <article className="metric-card"><Scale size={22} /><span>Massa gorda</span><strong>{formatValue(result.massaGorda, ' kg')}</strong><p>Massa magra: {formatValue(result.massaMagra, ' kg')}</p></article>
            <article className="metric-card"><BarChart3 size={22} /><span>Peso ideal</span><strong>{formatValue(result.pesoIdealMinimo, ' kg')}</strong><p>Faixa ate {formatValue(result.pesoIdealMaximo, ' kg')}</p></article>
          </div>
          <div className="report-grid">
            <article className="wide-panel"><h2>Relatorio corporal</h2><pre>{result.relatorioCorporal}</pre></article>
            <article className="wide-panel"><h2>Composicao corporal</h2><div className="body-bars"><span style={{ height: `${Math.min(100, result.peso)}%` }}>Atual<br />{result.peso} kg</span><span style={{ height: `${Math.min(100, result.pesoIdealMinimo)}%` }}>P.Min<br />{result.pesoIdealMinimo} kg</span><span style={{ height: `${Math.min(100, result.pesoIdealMaximo)}%` }}>P.Max<br />{result.pesoIdealMaximo} kg</span><span style={{ height: `${Math.min(100, result.massaGorda * 2)}%` }}>Gordo<br />{result.massaGorda} kg</span><span style={{ height: `${Math.min(100, result.massaMagra)}%` }}>Magro<br />{result.massaMagra} kg</span></div></article>
            <article className="wide-panel"><h2>Perimetros</h2><p>Pescoco: {result.pescoco} cm | Ombro: {result.ombro} cm | Torax: {result.torax} cm | Cintura: {result.cintura} cm | Abdomen: {result.abdomen} cm | Quadril: {result.quadril} cm</p><p>Braco D/E: {result.bracoDireito}/{result.bracoEsquerdo} cm | Coxa D/E: {result.coxaDireita}/{result.coxaEsquerda} cm | Panturrilha D/E: {result.panturrilhaDireita}/{result.panturrilhaEsquerda} cm</p></article>
            <article className="wide-panel"><h2>Dobras cutaneas</h2><p>Bicipital: {result.dobraBicipital} mm | Tricipital: {result.dobraTricipital} mm | Peitoral: {result.dobraPeitoral} mm | Subescapular: {result.dobraSubescapular} mm | Axilar media: {result.dobraAxilarMedia} mm</p><p>Suprailiaca: {result.dobraSuprailiaca} mm | Abdominal: {result.dobraAbdominal} mm | Coxa: {result.dobraCoxa} mm | Panturrilha: {result.dobraPanturrilha} mm</p></article>
          </div>
        </div>
      )}
    </section>
  );
}

function Training({ dashboard }) {
  return <section className="content-stack"><div className="section-heading"><span>Plano sugerido</span><h1>Treino</h1></div><article className="wide-panel"><h2>{dashboard.treinoRecomendado}</h2><pre>{dashboard.treinoCompleto}</pre></article></section>;
}

function Diet({ dashboard }) {
  return <section className="content-stack"><div className="section-heading"><span>Plano alimentar</span><h1>Dieta</h1></div><article className="wide-panel"><h2>{dashboard.dietaRecomendada}</h2><pre>{dashboard.dietaCompleta}</pre></article></section>;
}

function Evolution({ dashboard }) {
  const current = Number(dashboard.pesoAtual);
  const target = Number(dashboard.metaPeso);
  const progress = current && target ? Math.min(100, Math.max(10, 100 - Math.abs(current - target) * 8)) : 20;
  return <section className="content-stack"><div className="section-heading"><span>Acompanhamento</span><h1>Evolucao</h1></div><article className="wide-panel"><h2>{progress.toFixed(0)}% de aderencia inicial</h2><div className="progress large"><div><span style={{ width: `${progress}%` }} /></div></div><p>{dashboard.metaSemanalSugerida}</p><pre>{dashboard.relatorioCorporal}</pre></article></section>;
}

function Dashboard({ dashboard, onDashboardChange }) {
  const [active, setActive] = React.useState('dashboard');

  async function refreshDashboard() {
    const updated = await getDashboard(dashboard.usuarioId);
    onDashboardChange(updated);
  }

  return (
    <div className="app-shell">
      <Sidebar active={active} onChange={setActive} dashboard={dashboard} />
      <main className="dashboard-content">
        {active === 'dashboard' && <DashboardHome dashboard={dashboard} />}
        {active === 'avaliacao' && <PhysicalAssessment dashboard={dashboard} onSaved={refreshDashboard} />}
        {active === 'treino' && <Training dashboard={dashboard} />}
        {active === 'dieta' && <Diet dashboard={dashboard} />}
        {active === 'evolucao' && <Evolution dashboard={dashboard} />}
      </main>
    </div>
  );
}

export default function App() {
  const [screen, setScreen] = React.useState('public');
  const [dashboard, setDashboard] = React.useState(null);

  if (screen === 'register') {
    return <RegisterFlow onComplete={async (profile) => {
      const createdDashboard = await registerUser(profile);
      setDashboard(createdDashboard);
      setScreen('dashboard');
    }} />;
  }
  if (screen === 'dashboard' && dashboard) {
    return <Dashboard dashboard={dashboard} onDashboardChange={setDashboard} />;
  }
  return <PublicHome onStart={() => setScreen('register')} />;
}
