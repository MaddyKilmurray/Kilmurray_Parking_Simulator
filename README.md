### Parking Simulator

This project uses the following technologies:

- Java
- Springboot
- H2 Database
- Angular

Additionally, this project used the following external APIs:

- QR Code Generator: https://goqr.me/api/
- ZXing Scanner: https://zxing.org/w/decode.jspx



### Set-up

#### Backend Application

- Clone the backend repository: https://github.com/MaddyKilmurray/Kilmurray_Parking_Simulator
- Open each service, right-click on the pom.xml file and select "Add as Maven project"

- Select "Trust Project"
- Run each service

#### Frontend Application

- Clone the frontend repository: https://github.com/MaddyKilmurray/Kilmurray_Parking_Simulator_Frontend
- Install the dependencies: `npm install`
- Run the following command to start the frontend application: `npm start`
- The application will be available at: http://localhost:4200/



### Routes

#### Backend

| API Route                       | Route Type | Route Description                                  | Input        | Output    |
| ------------------------------- | ---------- | -------------------------------------------------- | ------------ | --------- |
| localhost:8500/api/parking/get  | GET        | Return all car records in server                   | NONE         | List<Car> |
| localhost:8500/api/create/entry | POST       | Register entry to the car park and save car record | EntryFormDTO | NONE      |
| localhost:8500/api/create/exit  | POST       | Register exit and confirm if charge is applicable  | ExitFormDTO  | Boolean   |

#### Entry Form DTO

```
{
    licencePlate: String,
    entranceDate: LocalDateTime,
    qrCode: String
}
```

#### Exit Form DTO

```
{
    qrCode: String
}
```

