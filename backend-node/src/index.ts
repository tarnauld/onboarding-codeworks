const express = require("express");
const app = express();
const port = 3000;
const promBundle = require("express-prom-bundle");

import { computeBills } from "./compute-bills";

const metricsMiddleware = promBundle({
  includeMethod: true,
  includePath: true,
  includeStatusCode: true,
  includeUp: true,
  customLabels: {
    project_name: "hello_world",
    project_type: "test_metrics_labels",
  },
  promClient: {
    collectDefaultMetrics: {},
  },
});

app.use(express.json());
app.use(metricsMiddleware)

app.get("/", (req: any, res: any) => {
  res.send({
      name: "Onboarding CodeWorks",
      version: "1.0"
  });
});

app.post("/compute-bills", (req: any, res: any) => {
  res.send(computeBills(req.body.shipping, req.body.items));
});

app.listen(port, () => {
  console.log(`App is listening on port ${port}`);
});
