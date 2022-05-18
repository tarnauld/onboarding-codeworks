FROM node:18
COPY . /usr/src/onboarding-codeworks
WORKDIR /usr/src/onboarding-codeworks
EXPOSE 3000
RUN npm i
RUN npm run build
CMD ["node", "dist/src/index.js"]