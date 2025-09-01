import http from 'k6/http';
import { check } from 'k6';

export const options = {
  thresholds: {
    // A taxa de falha (requisições com erro) deve ser menor que 1%
    http_req_failed: ['rate<0.01'],
    // O tempo de duração da requisição (p95) deve ser menor que 200ms
    http_req_duration: ['p(95)<200'],
    // Pelo menos 99% dos checks devem ser bem-sucedidos
    'checks': ['rate>0.99'],
  },
  stages: [
    // sobe de 0 para 20 usuários em 30s
    { duration: '30s', target: 20 },
    // mantém 20 usuários por 1 minuto
    { duration: '1m', target: 20 },
    // volta para 0 usuários em 30s
    { duration: '30s', target: 0 },
  ],
};

export default function () {
  const res = http.get('http://localhost:8080/expenses');

  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}