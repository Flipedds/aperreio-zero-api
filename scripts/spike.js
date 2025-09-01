import http from 'scripts/http';
import { check } from 'k6';

export const options = {
  stages: [
    // Sobe rapidamente para 5 usuários em 5s
    { duration: '5s', target: 5 },
    // sobe para 100 usuários em 2s
    { duration: '2s', target: 100 },
    // Mantém o pico por 10s
    { duration: '10s', target: 100 },
    // Volta para 5 usuários em 2s
    { duration: '2s', target: 5 },
  ],
};

export default function () {
  const res = http.get('http://localhost:8080/expenses');

  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}