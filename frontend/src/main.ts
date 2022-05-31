import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import { createVuestic } from "vuestic-ui";
import "vuestic-ui/dist/vuestic-ui.css";
import { OktaAuth } from "@okta/okta-auth-js";
import OktaVue from "@okta/okta-vue";

const oktaAuth = new OktaAuth({
  issuer: "https://dev-06452185.okta.com/oauth2/default",
  clientId: "0oa58c5rg5SuL2WcY5d7",
  redirectUri: window.location.origin + "/login/callback",
  scopes: ["openid", "profile", "email"],
});

const app = createApp(App);

app.use(OktaVue, { oktaAuth });
app.use(createPinia());
app.use(router);
app.use(createVuestic());

app.mount("#app");
