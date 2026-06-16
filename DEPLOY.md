# SkillBridge Deployment

## Backend (Railway)

1. Push this repo to GitHub (`riddhidayma/skillbridge-backend`).
2. In [Railway](https://railway.app), create a project and deploy from the GitHub repo.
3. Add a **MySQL** service and link it to the backend service.
4. Set environment variable on the backend service:
   - `APP_CORS_ALLOWED_ORIGINS` = `http://localhost:4200,https://skillbridge-sepia.vercel.app`
5. Railway auto-injects `MYSQLHOST`, `MYSQLPORT`, `MYSQLUSER`, `MYSQLPASSWORD`, `MYSQLDATABASE`.
6. Redeploy. Health check: `GET /api/workshops`

**Do not** set `SPRING_PROFILES_ACTIVE=local` on Railway (that profile is for local H2 dev only).

## Frontend (Vercel)

1. Push `riddhidayma/Skillbridge` (folder `skillbridge-frontend`) to GitHub.
2. In Vercel, import the repo and set root directory to `skillbridge-frontend`.
3. After Railway deploys, update `src/environments/environment.ts` with the new Railway API URL, or set a build env var and wire it in (current URL placeholder):
   - `https://skillbridge-backend-production-bd14.up.railway.app/api`
4. Redeploy Vercel.

## Local development

```bash
# Backend
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home
cd skill && ./run-backend.sh

# Frontend (separate terminal)
export PATH="/usr/local/opt/node@22/bin:$PATH"
cd skill-frontend && ./run-frontend.sh
```

- Frontend: http://localhost:4200
- Backend API: http://localhost:8080/api
