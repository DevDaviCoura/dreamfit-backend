const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

async function request(path, options = {}) {
  const response = await fetch(`${API_URL}${path}`, {
    headers: {
      'Content-Type': 'application/json',
      ...options.headers,
    },
    ...options,
  });

  if (!response.ok) {
    const error = await response.json().catch(() => ({ erro: 'Erro inesperado.' }));
    throw new Error(error.erro || 'Erro inesperado.');
  }

  return response.json();
}

export async function registerUser(profile) {
  return request('/usuarios/cadastrar', {
    method: 'POST',
    body: JSON.stringify({
      ...profile,
      idade: Number(profile.idade),
      peso: Number(profile.peso),
      altura: Number(profile.altura),
      metaPeso: Number(profile.metaPeso),
    }),
  });
}

export async function loginUser(credentials) {
  return request('/usuarios/login', {
    method: 'POST',
    body: JSON.stringify(credentials),
  });
}

export async function getDashboard(usuarioId) {
  return request(`/dashboard/${usuarioId}`);
}

export async function calculateImc({ peso, altura }) {
  return request('/imc', {
    method: 'POST',
    body: JSON.stringify({
      peso: Number(peso),
      altura: Number(altura),
    }),
  });
}

export async function createAssessment(usuarioId, assessment) {
  return request(`/avaliacoes/${usuarioId}`, {
    method: 'POST',
    body: JSON.stringify(Object.fromEntries(
      Object.entries(assessment).map(([key, value]) => [
        key,
        key === 'sexo' ? value : Number(value),
      ])
    )),
  });
}

export async function listAssessments(usuarioId) {
  return request(`/avaliacoes/${usuarioId}`);
}
