

### Create a service account
```bash
gcloud iam service-accounts create <ACCOUNT_NAME>
```

### Add a policy
```bash
gcloud projects add-iam-policy-binding <PROJECT_ID> --member="serviceAccount:<ACCOUNT_NAME>@<PROJECT_ID>.iam.gserviceaccount.com" --role=<ROLE>
```

### Create a file with the credentials
```bash
gcloud iam service-accounts keys create <FILE_NAME>.json --iam-account=<ACCOUNT_NAME>@<PROJECT_ID>.iam.gserviceaccount.com
```


### Define a variable to get access to your credentials
```bash
export GOOGLE_APPLICATION_CREDENTIALS="KEY_PATH"
```
