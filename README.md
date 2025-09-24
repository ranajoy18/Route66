# Route66 Test Automation

This repository contains an automated test suite built with Maven, Selenium, and TestNG/Cucumber. A GitHub Actions workflow is included so you can trigger the tests manually from the Actions tab and download the resulting reports.

## Running the tests locally

```bash
mvn test
```

The Extent Reports HTML summary is generated at `extent-report.html`, while additional TestNG output is stored under `test-output/` and JUnit-style reports appear in `target/surefire-reports/`.

## GitHub Actions workflow

The workflow defined in [`.github/workflows/manual-test-run.yml`](.github/workflows/manual-test-run.yml) runs on demand through the `workflow_dispatch` trigger. It performs the following tasks:

1. Checks out the repository sources.
2. Sets up Temurin Java 17 with Maven dependency caching enabled.
3. Executes `mvn -B test` to run the automated test suite.
4. Uploads the Extent Reports HTML file, the `test-output` directory, and the Surefire reports as downloadable artifacts, even if the tests fail.

### Triggering the workflow manually

1. Push the workflow file to your GitHub repository (instructions below).
2. Navigate to **Actions** in your GitHub repository and click **I understand my workflows, go ahead and enable them** if prompted.
3. Select the **Manual Test Run** workflow from the left sidebar.
4. Click **Run workflow**.
5. (Optional) Supply a browser name or tag filters for the run, then click **Run workflow** again to start the job.
6. Wait for the workflow to complete. The job status (success or failure) reflects the test results.
7. Download the `test-reports` artifact from the workflow run summary to inspect the generated HTML and XML reports.

### Uploading the workflow to GitHub

1. Ensure you have committed the workflow file locally.
2. Push your branch to GitHub: `git push origin <branch-name>`.
3. Create a pull request if needed, and merge it into your default branch.

No additional GitHub secrets or manual configuration are required unless your tests need access to private resources.
