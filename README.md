# Service Account Key 만들기
1. [Service Account Console](https://console.cloud.google.com/iam-admin/serviceaccounts) 에서 계정 Service Account 만들기
   1. `pubsub-sa` 로 만들었습니다. gcloud 명령어에 같은 이름을 적어주어야 합니다

2. [IAM service account](https://cloud.google.com/iam/docs/creating-managing-service-account-keys?hl=ko#iam-service-account-keys-create-gcloud) 콘솔에서 .json 키 만들기
    ```shell
    gcloud iam service-accounts keys create pubsub-sa-file.json \
    --iam-account=pubsub-sa@projecttest-325212.iam.gserviceaccount.com
    ```
   **(1)에서 만든 것과 다른 이름을 적는 경우 다음 에러가 나타납니다**
   ```shell
   ERROR: (gcloud.iam.service-accounts.keys.create) NOT_FOUND: Not found; 
   Gaia id not found for email pubsub-sa3@projecttest-325212.iam.gserviceaccount.com
   ```
