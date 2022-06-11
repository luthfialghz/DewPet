## DewPet API

This API is intended for the purposes of our capstone project at Bangkit Academy 2022

## Table of Contents

* [Routes](#routes)
* [API Documentation](#api-documentation)
* [Contributor](#contributor)

## Routes

*Still work on progress*
| HTTP METHOD | POST            | GET       | PUT         | DELETE |
| ----------- | :-------: | :------:  | :------:  | :------: |
| /regis       | Add User | - | - | - |
| /login  | Login | Info User | - | - |
| /listgejala1  | - | List of Symptoms and Diseases | - | - |
| /listgejala2  | - | List of Symptoms and Diseases Translate | - | - |
| /predict | Disease Prediction | - | - | - |
| /vet | - | List veterinarians | - | - |
| /jadwalcek  | - | List Schedule | - | - |
| /appointment  | Add Appointment | - | - | - |
| /jadwalbook  | Check Appointment | - | - | - |
| /edited/`<int:id>`  | - | - | Update appointment | - |
| /clientlist  | - | List Client | - | - |
| /hapus/`<int:id>`  | - | - | - | Delete appointment |
| /articles  | - | List Articles | - | - |


## API Documentation 
*Still work on progress*
### List of Endpoints
* [Login](#login)
    * [Registration](#registration)
    * [Login](#login)
    * [Info User](#info-user)
* [Diagnosis](#diagnosis)
    * [Get All Symptoms and Diseases](#get-all-symptoms-and-diseases)
    * [Get All Symptoms and Diseases Translate](#get-all-symptoms-and-diseases-translate)
    * [Disease Prediction](#disease-prediction)
* [Appointment](#appointment)
    * [Get All Veterinarians](#get-all-veterinarians)
    * [Get All Schedule](#get-all-schedule)
    * [Update Appointment](#update-appointment)
    * [Modify Appointment](#modify-appointment)
    * [Delete Appointment](#delete-appointment)
    * [Check Schedule](#check-schedule)
    * [Check Client Appointment](#check-client-appointment)
    * [Approve Appointment](#approve-appointment)
    
* [Articles](#articles)
    * [Get All Articles](#get-all-articles)

## Login

### Registration
* Method : POST
* URL : `/regis`    
* Request body :
```json 
{
    "email": "shintest@gmail.com",
    "password": "a6123",
    "namaL": "drh. shin ryujin M.PD"
}
```
* Response body:
```json
{
    "status": "Success",
    "data": {
        "email": "dannydarmawan359@gmail.com",
        "password": "123456",
        "role": "user",
        "namaL": "Danny Darmawan Putra",
        "token": "28dbd996efd3d179b0865af490b1e9ddc1c8ee4751ea4a2dc81b3d404f15bfd1bdfab0a66315b1fa0885625e64f7318f60914e04af5d6e1d46394b47718c6e8d"
    }
}
``` 

### Login
* Method : POST
* URL : `/login`    
* Request body :
```json 
{
    "email": "dannydarmawan359@gmail.com",
    "password": "123456"
}
```
* Response body:
```json
{
    "email": "dannydarmawan359@gmail.com",
    "password": "123456",
    "role": "user",
    "namaL": "Danny Darmawan Putra",
    "token": "28dbd996efd3d179b0865af490b1e9ddc1c8ee4751ea4a2dc81b3d404f15bfd1bdfab0a66315b1fa0885625e64f7318f60914e04af5d6e1d46394b47718c6e8d"
}
``` 

### Info User
* Method : POST
* URL : `/login/checklogin`    
* Request body :
```json 
{
    "token": "28dbd996efd3d179b0865af490b1e9ddc1c8ee4751ea4a2dc81b3d404f15bfd1bdfab0a66315b1fa0885625e64f7318f60914e04af5d6e1d46394b47718c6e8d"
}
```
* Response body:
```json
{
    "email": "dannydarmawan359@gmail.com",
    "password": "123456",
    "role": "user",
    "namaL": "Danny Darmawan Putra",
    "token": "28dbd996efd3d179b0865af490b1e9ddc1c8ee4751ea4a2dc81b3d404f15bfd1bdfab0a66315b1fa0885625e64f7318f60914e04af5d6e1d46394b47718c6e8d"
}
``` 


## Diagnosis

### Get All Symptoms and Diseases
* Method : GET
* URL : `/listgejala1`    
* Response body :
```json
{
    "gejala": [
        "Abdominal Pain",
        "Abnormal Elevation of Inner Eyelid",
        "Abnormal Coloration of One or More Teeth",
        "Abnormal Posture",
        "Abnormal Nail Color",
        "Bad Odor",
        "Bad Breath",
        "Bleeding",
        "Bleeding Gums",
        "Blood in Urine/Feses",
        "Bruises",
        "Crusty Skin",
        "Constipation",
        "Coughing",
        "Crust in The Ear",
        "Depression",
        "Diarrhea",
        "Dehydration",
        "Difficult Urination",
        "Difficulty Swallowing",
        "Dilated Pupil",
        "Discolored Urine",
        "Dry Eye",
        "Drainage",
        "Drooling",
        "Dropping Food",
        "Ear Infection",
        "Ear Redness",
        "Ear Odor",
        "Enlarged Bladder",
        "Excessive Ear Wax",
        "Ear Discharge",
        "Ear Swelling",
        "Ear Mite Infection",
        "Ear Inflammation",
        "Excessive Blinking",
        "Eye Redness",
        "Eye Swelling",
        "Eye Inflammation",
        "Eye Discharge",
        "Eye Excessive Watering",
        "Facial Swelling",
        "Fever",
        "Fractured Tooth",
        "Frequent Thirst",
        "Foul-smelling Urine",
        "Greasy Skin",
        "Grinding teeth",
        "Hair Loss",
        "Head Tilt",
        "Hunched Posture",
        "Impaired Vision",
        "Infections on the face or neck area",
        "Increased Frequency of Urination",
        "Itching Ear",
        "Itching Eye",
        "Lameness",
        "Licking Excessively",
        "Licking at The Paws",
        "Lethargy",
        "Lesions Eye",
        "Lesions Skin",
        "Loose Teeth",
        "Loss of Balance",
        "Loss of Appetite",
        "Lower Back Pain",
        "Mucous Eye",
        "Nasal Discharge",
        "Noisy Breathing",
        "Obesity",
        "Pain",
        "Panting",
        "Protrusion",
        "Runny Nose",
        "Scratching",
        "Smaller Size Pupil",
        "Skin Infection",
        "Skin Redness",
        "Skin Odor",
        "Skin Loose Around Eye",
        "Sagging",
        "Swollen Gums",
        "Swelling",
        "Squinting",
        "Seizures",
        "Unwillingness to Move",
        "Vomiting",
        "Vision Loss/Blindness",
        "Weight Loss",
        "Whitish-appearing Pupil",
        "Yeasty Smell"
    ],
    "penyakit": [
        "Anal sac abscess",
        "Apical Abscesses",
        "Atopic Dermatitis",
        "Bacterial Infection",
        "Bacterial Infection (Actinomycosis)",
        "Blepharitis",
        "Cataracts",
        "Chiggers",
        "Chronic Ulcerative Paradental Stomatitis",
        "Claw and Nail Disorders",
        "Cleft Palate",
        "Conjunctivitis",
        "Dental abscesses",
        "Dry Eye Syndrome",
        "Ear Hematomas",
        "Ear Infections",
        "Ear Mites",
        "Ectropion",
        "Elongation",
        "Entropion",
        "Epiphora",
        "Epulis",
        "Exophthalmos",
        "Eyeworms",
        "Gingivitis",
        "Glaucoma",
        "Halitosis",
        "Horner’s Syndrome",
        "Hyperemia",
        "Incisor Malocclusion",
        "Inflammation of the Eye",
        "Iris Bombe - Complete Posterior Synechiae",
        "Laryngeal Paralysis",
        "Lockjaw",
        "Molar",
        "Nail Disorders",
        "Oral Ulceration",
        "Otitis Externa",
        "Otitis Interna",
        "Otitis Media",
        "Premolar",
        "Pruritus",
        "Ptyalism",
        "Pyoderma",
        "Red Eye",
        "Retinal Hemorrhage",
        "Stomatitis",
        "Tooth Abscess",
        "Tooth Resorption",
        "Uveitis",
        "Vestibular Disease"
    ]
}
```

### Get All Symptoms and Diseases Translate
* Method : GET
* URL : `/listgejala2`
* Response body :
```json
{
    "gejala": [
        {
            "id": 1,
            "nama_gejala": "Sakit perut"
        },
        {
            "id": 2,
            "nama_gejala": "Ketinggian Abnormal Kelopak Mata Dalam"
        },
        {
            "id": 3,
            "nama_gejala": "Warna Abnormal Satu atau Lebih Gigi"
        },
        {
            "id": 4,
            "nama_gejala": "Postur Tidak Normal"
        },
        {
            "id": 5,
            "nama_gejala": "Warna Kuku Tidak Normal"
        },
        {
            "id": 6,
            "nama_gejala": "Bau busuk"
        },
        {
            "id": 7,
            "nama_gejala": "Bau mulut"
        },
        {
            "id": 8,
            "nama_gejala": "Berdarah"
        },
        {
            "id": 9,
            "nama_gejala": "Gusi berdarah"
        },
        {
            "id": 10,
            "nama_gejala": "Darah dalam Urine / Feses"
        },
        {
            "id": 11,
            "nama_gejala": "memar"
        },
        {
            "id": 12,
            "nama_gejala": "Kulit Berkerak"
        },
        {
            "id": 13,
            "nama_gejala": "Sembelit"
        },
        {
            "id": 14,
            "nama_gejala": "Batuk"
        },
        {
            "id": 15,
            "nama_gejala": "Kerak di Telinga"
        },
        {
            "id": 16,
            "nama_gejala": "Depresi"
        },
        {
            "id": 17,
            "nama_gejala": "Diare"
        },
        {
            "id": 18,
            "nama_gejala": "Dehidrasi"
        },
        {
            "id": 19,
            "nama_gejala": "Sulit buang air kecil"
        },
        {
            "id": 20,
            "nama_gejala": "Kesulitan Menelan"
        },
        {
            "id": 21,
            "nama_gejala": "Pupil melebar"
        },
        {
            "id": 22,
            "nama_gejala": "Urine Berubah Warna"
        },
        {
            "id": 23,
            "nama_gejala": "Mata kering"
        },
        {
            "id": 24,
            "nama_gejala": "Drainase"
        },
        {
            "id": 25,
            "nama_gejala": "ngiler"
        },
        {
            "id": 26,
            "nama_gejala": "Menjatuhkan Makanan"
        },
        {
            "id": 27,
            "nama_gejala": "Infeksi telinga"
        },
        {
            "id": 28,
            "nama_gejala": "Telinga Kemerahan"
        },
        {
            "id": 29,
            "nama_gejala": "Bau Telinga"
        },
        {
            "id": 30,
            "nama_gejala": "Kandung kemih yang membesar"
        },
        {
            "id": 31,
            "nama_gejala": "Kotoran Telinga Berlebihan"
        },
        {
            "id": 32,
            "nama_gejala": "Debit Telinga"
        },
        {
            "id": 33,
            "nama_gejala": "Pembengkakan Telinga"
        },
        {
            "id": 34,
            "nama_gejala": "Infeksi Kutu Telinga"
        },
        {
            "id": 35,
            "nama_gejala": "radang telinga"
        },
        {
            "id": 36,
            "nama_gejala": "Berkedip berlebihan"
        },
        {
            "id": 37,
            "nama_gejala": "Mata Kemerahan"
        },
        {
            "id": 38,
            "nama_gejala": "Pembengkakan Mata"
        },
        {
            "id": 39,
            "nama_gejala": "Peradangan mata"
        },
        {
            "id": 40,
            "nama_gejala": "Debit Mata"
        },
        {
            "id": 41,
            "nama_gejala": "Penyiraman Mata Berlebihan"
        },
        {
            "id": 42,
            "nama_gejala": "Pembengkakan wajah"
        },
        {
            "id": 43,
            "nama_gejala": "Demam"
        },
        {
            "id": 44,
            "nama_gejala": "Gigi Patah"
        },
        {
            "id": 45,
            "nama_gejala": "Sering Haus"
        },
        {
            "id": 46,
            "nama_gejala": "Urine berbau busuk"
        },
        {
            "id": 47,
            "nama_gejala": "Kulit Berminyak"
        },
        {
            "id": 48,
            "nama_gejala": "Menggertakkan gigi"
        },
        {
            "id": 49,
            "nama_gejala": "Rambut rontok"
        },
        {
            "id": 50,
            "nama_gejala": "Memiringkan kepala"
        },
        {
            "id": 51,
            "nama_gejala": "Postur Membungkuk"
        },
        {
            "id": 52,
            "nama_gejala": "Gangguan penglihatan"
        },
        {
            "id": 53,
            "nama_gejala": "Infeksi pada area wajah atau leher"
        },
        {
            "id": 54,
            "nama_gejala": "Peningkatan Frekuensi Buang Air Kecil"
        },
        {
            "id": 55,
            "nama_gejala": "telinga gatal"
        },
        {
            "id": 56,
            "nama_gejala": "Mata Gatal"
        },
        {
            "id": 57,
            "nama_gejala": "Ketimpangan"
        },
        {
            "id": 58,
            "nama_gejala": "Menjilat Secara Berlebihan"
        },
        {
            "id": 59,
            "nama_gejala": "Menjilat di The Paws"
        },
        {
            "id": 60,
            "nama_gejala": "Kelesuan"
        },
        {
            "id": 61,
            "nama_gejala": "Mata Lesi"
        },
        {
            "id": 62,
            "nama_gejala": "Kulit Lesi"
        },
        {
            "id": 63,
            "nama_gejala": "gigi goyang"
        },
        {
            "id": 64,
            "nama_gejala": "Kehilangan Saldo"
        },
        {
            "id": 65,
            "nama_gejala": "Kehilangan selera makan"
        },
        {
            "id": 66,
            "nama_gejala": "Nyeri Punggung Bawah"
        },
        {
            "id": 67,
            "nama_gejala": "Mata berlendir"
        },
        {
            "id": 68,
            "nama_gejala": "Debit Hidung"
        },
        {
            "id": 69,
            "nama_gejala": "Pernapasan Bising"
        },
        {
            "id": 70,
            "nama_gejala": "Kegemukan"
        },
        {
            "id": 71,
            "nama_gejala": "Rasa sakit"
        },
        {
            "id": 72,
            "nama_gejala": "terengah-engah"
        },
        {
            "id": 73,
            "nama_gejala": "tonjolan"
        },
        {
            "id": 74,
            "nama_gejala": "Pilek"
        },
        {
            "id": 75,
            "nama_gejala": "Goresan"
        },
        {
            "id": 76,
            "nama_gejala": "Murid Ukuran Lebih Kecil"
        },
        {
            "id": 77,
            "nama_gejala": "Infeksi kulit"
        },
        {
            "id": 78,
            "nama_gejala": "Kulit Kemerahan"
        },
        {
            "id": 79,
            "nama_gejala": "Bau Kulit"
        },
        {
            "id": 80,
            "nama_gejala": "Kulit Longgar Di Sekitar Mata"
        },
        {
            "id": 81,
            "nama_gejala": "Menurun"
        },
        {
            "id": 82,
            "nama_gejala": "Gusi bengkak"
        },
        {
            "id": 83,
            "nama_gejala": "Pembengkakan"
        },
        {
            "id": 84,
            "nama_gejala": "menyipitkan mata"
        },
        {
            "id": 85,
            "nama_gejala": "kejang"
        },
        {
            "id": 86,
            "nama_gejala": "Keengganan untuk Bergerak"
        },
        {
            "id": 87,
            "nama_gejala": "muntah"
        },
        {
            "id": 88,
            "nama_gejala": "Kehilangan Penglihatan/Kebutaan"
        },
        {
            "id": 89,
            "nama_gejala": "Penurunan Berat Badan"
        },
        {
            "id": 90,
            "nama_gejala": "Murid yang tampak keputihan"
        },
        {
            "id": 91,
            "nama_gejala": "Bau Ragi"
        }
    ],
    "penyakit": [
        {
            "id": 1,
            "nama_penyakit": "Abses kantung anus"
        },
        {
            "id": 2,
            "nama_penyakit": "Abses Apikal"
        },
        {
            "id": 3,
            "nama_penyakit": "Dermatitis atopik"
        },
        {
            "id": 4,
            "nama_penyakit": "Infeksi bakteri"
        },
        {
            "id": 5,
            "nama_penyakit": "Infeksi Bakteri (Aktinomikosis)"
        },
        {
            "id": 6,
            "nama_penyakit": "Blefaritis"
        },
        {
            "id": 7,
            "nama_penyakit": "katarak"
        },
        {
            "id": 8,
            "nama_penyakit": "Chiggers"
        },
        {
            "id": 9,
            "nama_penyakit": "Stomatitis Paradental Ulseratif Kronis"
        },
        {
            "id": 10,
            "nama_penyakit": "Cakar dan Gangguan Kuku"
        },
        {
            "id": 11,
            "nama_penyakit": "langit-langit sumbing"
        },
        {
            "id": 12,
            "nama_penyakit": "Konjungtivitis"
        },
        {
            "id": 13,
            "nama_penyakit": "Abses gigi"
        },
        {
            "id": 14,
            "nama_penyakit": "Sindrom mata kering"
        },
        {
            "id": 15,
            "nama_penyakit": "Hematoma Telinga"
        },
        {
            "id": 16,
            "nama_penyakit": "Infeksi Telinga"
        },
        {
            "id": 17,
            "nama_penyakit": "Tungau Telinga"
        },
        {
            "id": 18,
            "nama_penyakit": "ektropion"
        },
        {
            "id": 19,
            "nama_penyakit": "Pemanjangan"
        },
        {
            "id": 20,
            "nama_penyakit": "entropion"
        },
        {
            "id": 21,
            "nama_penyakit": "Epifora"
        },
        {
            "id": 22,
            "nama_penyakit": "makanan mewah"
        },
        {
            "id": 23,
            "nama_penyakit": "Eksoftalmus"
        },
        {
            "id": 24,
            "nama_penyakit": "cacing mata"
        },
        {
            "id": 25,
            "nama_penyakit": "Radang gusi"
        },
        {
            "id": 26,
            "nama_penyakit": "Glaukoma"
        },
        {
            "id": 27,
            "nama_penyakit": "Mulut berbau"
        },
        {
            "id": 28,
            "nama_penyakit": "Sindrom Horner"
        },
        {
            "id": 29,
            "nama_penyakit": "Hiperemia"
        },
        {
            "id": 30,
            "nama_penyakit": "Maloklusi Insisivus"
        },
        {
            "id": 31,
            "nama_penyakit": "Peradangan Mata"
        },
        {
            "id": 32,
            "nama_penyakit": "Iris Bombe - Synechiae Posterior Lengkap"
        },
        {
            "id": 33,
            "nama_penyakit": "Kelumpuhan laring"
        },
        {
            "id": 34,
            "nama_penyakit": "Penyakit kejang mulut"
        },
        {
            "id": 35,
            "nama_penyakit": "Geraham"
        },
        {
            "id": 36,
            "nama_penyakit": "Gangguan Kuku"
        },
        {
            "id": 37,
            "nama_penyakit": "Ulserasi Mulut"
        },
        {
            "id": 38,
            "nama_penyakit": "Otitis Eksternal"
        },
        {
            "id": 39,
            "nama_penyakit": "Otitis Internal"
        },
        {
            "id": 40,
            "nama_penyakit": "Otitis Media"
        },
        {
            "id": 41,
            "nama_penyakit": "Premolar"
        },
        {
            "id": 42,
            "nama_penyakit": "pruritus"
        },
        {
            "id": 43,
            "nama_penyakit": "Ptyalisme"
        },
        {
            "id": 44,
            "nama_penyakit": "pioderma"
        },
        {
            "id": 45,
            "nama_penyakit": "Mata merah"
        },
        {
            "id": 46,
            "nama_penyakit": "Perdarahan Retina"
        },
        {
            "id": 47,
            "nama_penyakit": "stomatitis"
        },
        {
            "id": 48,
            "nama_penyakit": "Abses gigi"
        },
        {
            "id": 49,
            "nama_penyakit": "Resorpsi gigi"
        },
        {
            "id": 50,
            "nama_penyakit": "Uveitis"
        },
        {
            "id": 51,
            "nama_penyakit": "Penyakit vestibular"
        }
    ]
}
```

### Disease Prediction
* Method : POST
* URL : `/predict`
* Request body :
```json
{
    "penyakit": ["Mata kering"],
    "hewan": "kucing"
}
```
* Response body:
```json
{
    "hasil": [
        "katarak"
    ],
    "kategori": "Major"
}
```


## Appointment

### Get All Veterinarians
* Method : GET
* URL : `/vet`    
* Response body :
```json
{
    "vet": [
        {
            "vet_id": 12334,
            "namaL": "drh. shin ryujin M.Pd",
            "email": "shintest@gmail.com",
            "number_hp": "085675497515"
        },
        {
            "vet_id": 12335,
            "namaL": "drh. Eka",
            "email": "ekatest@gmail.com",
            "number_hp": "0876548795788"
        }
    ]
}
```

### Get All Schedule
* Method : GET
* URL : `/jadwalcek`    
* Response body :
```json
{
    "jadwal": [
        {
            "namaL": "drh. shin ryujin M.Pd",
            "number_hp": "085675497515",
            "email": "shintest@gmail.com",
            "service_id": 1,
            "location": "Jl. Kamboja No.2C,RW.1",
            "day": "Senin s/d Sabtu",
            "open": "08:00:00",
            "close": "16:00:00"
        },
        {
            "namaL": "drh. Eka",
            "number_hp": "0876548795788",
            "email": "ekatest@gmail.com",
            "service_id": 2,
            "location": "Jl. Ampera Raya No.5-6,RT.5/RW.2",
            "day": "Senin s/d Jum'at",
            "open": "09:00:00",
            "close": "20:00:00"
        }
    ]
}
```

### Update Appointment
* Method : POST
* URL : `/appointment`    
* Request body :
```json
{
    "client_email": "dannydarmawan359@gmail.com",
    "start_at": "2022-6-13 15:00",
    "message": "kucing saya matanya kering",
    "service_id": 1
}
```
* Response body :
```json
{
    "status": "Success",
    "data": {
        "fieldCount": 0,
        "affectedRows": 1,
        "insertId": 28,
        "serverStatus": 2,
        "warningCount": 0,
        "message": "",
        "protocol41": true,
        "changedRows": 0
    }
}
```

### Modify Appointment
* Method : POST
* URL : `/edited`    
* Request body :
```json
{
    "start_at": "2022-9-13 9:00",
    "message": "kucing saya matanya kering dan batuk",
    "service_id": 1,
    "id": 13
}
```
* Response body :
```json
{
    "status": "Success",
    "data": {
        "fieldCount": 0,
        "affectedRows": 0,
        "insertId": 0,
        "serverStatus": 2,
        "warningCount": 0,
        "message": "(Rows matched: 0  Changed: 0  Warnings: 0",
        "protocol41": true,
        "changedRows": 0
    }
}
```

### Delete Appointment
* Method : DEL
* URL : `/hapus/<int:id>`
* Response body :
```json
{
    "status": "Success",
    "data": [
        {
            "id": 28,
            "service_id": 1,
            "namaL": "drh. shin ryujin M.Pd",
            "email": "shintest@gmail.com",
            "modified_at": "2022-06-10T06:27:38.000Z",
            "start_at": "2022-06-13T15:00:00.000Z",
            "approved": "waiting",
            "location": "Jl. Kamboja No.2C,RW.1",
            "message": "kucing saya matanya kering"
        }
    ]
}
```

### Check Schedule
* Method : POST
* URL : `/jadwalbook`    
* Request body :
```json
{
    "client_email": "dannydarmawan359@gmail.com"
}
```
* Response body :
```json
{
    "status": "Success",
    "data": [
        {
            "id": 28,
            "service_id": 1,
            "namaL": "drh. shin ryujin M.Pd",
            "email": "shintest@gmail.com",
            "modified_at": "2022-06-10T06:27:38.000Z",
            "start_at": "2022-06-13T15:00:00.000Z",
            "approved": "waiting",
            "location": "Jl. Kamboja No.2C,RW.1",
            "message": "kucing saya matanya kering"
        }
    ]
}
```

### Check Client Appointment
* Method : GET
* URL : `/clientlist`    
* Response body :
```json
{
    "status": "Success",
    "data": [
        {
            "id": 28,
            "client_email": "dannydarmawan359@gmail.com",
            "namaL": "drh. shin ryujin M.Pd",
            "modified_at": "2022-06-10T06:27:38.000Z",
            "start_at": "2022-06-13T15:00:00.000Z",
            "approved": "yes",
            "message": "kucing saya matanya kering"
        },
        {
            "id": 29,
            "client_email": "luthfialghz@gmail.com",
            "namaL": "drh. shin ryujin M.Pd",
            "modified_at": "2022-06-10T12:16:34.000Z",
            "start_at": "2022-05-10T07:15:00.000Z",
            "approved": "waiting",
            "message": "gasgsaga"
        }
    ]
}
```

### Approve Appointment
* Method : PUT
* URL : `/approve/<int:id>`
* Response body :
* Request body :
```json
{
    "approved": "yes"
}
```
* Response body :
```json
{
    "status": "Success",
    "data": {
        "fieldCount": 0,
        "affectedRows": 1,
        "insertId": 0,
        "serverStatus": 2,
        "warningCount": 0,
        "message": "(Rows matched: 1  Changed: 1  Warnings: 0",
        "protocol41": true,
        "changedRows": 1
    }
}
```


## Articles

### Get All Articles
* Method : GET
* URL : `/articles`
* Response body :
```json
{
    "status": "ok",
    "totalResults": 17,
    "articles": [
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Surya Aditiya",
            "title": "Viral Kucing Liar Dijadikan Pakan Ular Peliharaan",
            "description": "Viral di media sosial seorang yang memelihara ular memberikan peliharaannya itu santapan berupa seekor kucing liar, orang tersebut panen hujatan dari warganet.",
            "url": "https://www.viva.co.id/trending/1480728-viral-kucing-liar-dijadikan-pakan-ular-peliharaan",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2022/06/02/629846fd13ba0-pemilik-ular-memberi-makan-binatang-peliharaannya-seekor-kucing-liar_665_374.jpg",
            "publishedAt": "2022-06-02T05:46:12Z",
            "content": "VIVA  Baru-baru ini viral di media sosial seorang yang memelihara ular memberikan peliharaannya itu santapan berupa seekor kucing liar. Kejadian ini membuat geram warganet khususnya para pecinta kuci… [+1214 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Dedi",
            "title": "Cari Perhatian? 4 Alasan Kucing Suka Menggosokkan Tubuhnya ke Majikan",
            "description": "Kucing merupakan salah satu hewan peliharaan yang dimiliki oleh banyak orang. Wajahnya yang sangat menggemaskan dan badannya yang berbulu membuat orang selalu gemas.",
            "url": "https://www.viva.co.id/gaya-hidup/inspirasi-unik/1473241-cari-perhatian-4-alasan-kucing-suka-menggosokkan-tubuhnya-ke-majikan",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2018/08/09/5b6bd2a265165-kucing-di-dalam-kardus_665_374.jpg",
            "publishedAt": "2022-05-10T00:10:02Z",
            "content": "VIVA  Kucing merupakan salah satu hewan peliharaan yang dimiliki oleh banyak orang. Wajahnya yang sangat menggemaskan dan badannya yang berbulu membuat orang-orang selalu gemas ketika melihatnya. Tap… [+1298 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Agus Setiawan",
            "title": "Seperti Pororo, Ini 5 Jenis Kucing yang Paling Populer di Indonesia",
            "description": "Kucing adalah salah satu hewan peliharaan yang cukup populer. Ada beragam jenis kucing yang populer dan dipelihara di Indonesia, salah satunya kucing anggora.",
            "url": "https://www.viva.co.id/gaya-hidup/inspirasi-unik/1480087-seperti-pororo-ini-5-jenis-kucing-yang-paling-populer-di-indonesia",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2022/05/31/629580d7e4d45-kucing-kampung_665_374.jpg",
            "publishedAt": "2022-05-31T05:43:56Z",
            "content": "VIVA  Kucing adalah salah satu hewan peliharaan yang cukup populer di Indonesia. Bagi pecinta kucing, hal terbaik akan dilakukan demi peliharaan kucing kesayangannya.\r\nTingkah kucing yang lucu dan me… [+658 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Dian Lestari Ningsih",
            "title": "Hukum Muslim Memelihara Anjing, Ini Kata UAS dan Buya Yahya",
            "description": "Buya Yahya menyarankan, jika ingin memelihara hewan, lebih baik yang halal, seperti beberapa di antaranya kucing, kambing, kelinci, atau lainnya. Jangan anjing.",
            "url": "https://www.viva.co.id/gaya-hidup/inspirasi-unik/1478301-hukum-muslim-memelihara-anjing-ini-kata-uas-dan-buya-yahya",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2020/11/12/5face1c461ead-ilustrasi-hewan-peliharaan-anjing_665_374.jpg",
            "publishedAt": "2022-05-24T23:02:34Z",
            "content": "VIVA  Selain kucing, masih banyak jenis hewan peliharaan yang kerap dijadikan teman atau penjaga di dalam rumahnya. Sebut saja salah satunya anjing.  Anjing merupakan salah satu jenis hewan yang kera… [+1130 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Adinda Permatasari",
            "title": "Hukum Muslim Pelihara Anjing hingga Misteri Sungai Siak",
            "description": "Hukum dalam memelihara anjing yang dijelaskan oleh Ustaz Abdul Somad alias UAS dan Buya Yahya pun menarik perhatian banyak pembaca.",
            "url": "https://www.viva.co.id/gaya-hidup/1478694-hukum-muslim-pelihara-anjing-hingga-misteri-sungai-siak",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2020/11/12/5face1c461ead-ilustrasi-hewan-peliharaan-anjing_665_374.jpg",
            "publishedAt": "2022-05-25T22:40:02Z",
            "content": "VIVA  Bagi umat Islam, anjing merupakan binatang yang diharamkan. Hukum dalam memelihara anjing yang dijelaskan oleh Ustaz Abdul Somad alias UAS dan Buya Yahya pun menarik perhatian banyak pembaca.\r\n… [+725 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Tamara Amalia",
            "title": "Hukum Jual Beli Kucing, Boleh atau Dilarang?",
            "description": "Berita Hukum Jual Beli Kucing, Boleh atau Dilarang? terbaru hari ini 2022-05-11 19:49:42 dari sumber yang terpercaya",
            "url": "https://www.viva.co.id/edukasi/1474160-hukum-jual-beli-kucing",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2021/02/21/60321a80b0627-resep-makanan-kucing-yang-bernutrisi-dan-mudah-dibuat-di-rumah_665_374.jpg",
            "publishedAt": "2022-05-11T12:49:42Z",
            "content": "VIVA  Hukum Jual BeliKucing menjadi suatu pembahasan yang banyak diperbincangkan. Apalagi, di Indonesia, sangat banyak masyarakatny yang memelihara kucing. Kucing merupan hewan karnivora dengan statu… [+1436 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Dian Lestari Ningsih",
            "title": "Diisukan Tak Punya Anak karena Kucing, Fanny Ghassani Buka Suara",
            "description": "Artis cantik Fanny Ghassani baru-baru ini memberikan sentilan pedas kepada warganet yang selalu menyinggung dirinya, perihal tak kunjung memiliki momongan.",
            "url": "https://www.viva.co.id/showbiz/gosip/1475176-diisukan-tak-punya-anak-karena-kucing-fanny-ghassani-buka-suara",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2019/09/10/5d77af115f389-fanny-ghassani_665_374.jpg",
            "publishedAt": "2022-05-14T14:46:05Z",
            "content": "VIVA  Artis cantik Fanny Ghassani baru-baru ini memberikan sentilan pedas kepada warganet yang selalu menyinggung dirinya, perihal tak kunjung memiliki momongan. Tak sampai di situ, banyak warganet y… [+1112 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Tamara Amalia",
            "title": "Viral, Wanita Ini Asik Digelendotin Ular saat Main HP",
            "description": "Sebuah video di Tiktok viral memperlihatkan seorang wanita yang asik bermain HP ketika digeledotin oleh seekor ular raksasa. Namun wanita tersebut tetap santai",
            "url": "https://www.viva.co.id/trending/1474522-viral-wanita-ini-asik-digelendotin-ular-saat-main-hp",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2022/05/12/627ce0943b851-ular-raksasa-mendekati-wanita-yang-asik-bermain-hp_665_374.jpeg",
            "publishedAt": "2022-05-12T22:32:02Z",
            "content": "VIVA  Sebuah video Tiktok yang viral memperlihatkan seorang wanita yang asik bermain HP ketika digeledotin oleh seekor ular raksasa. Akun Tiktok dengan username @inzaghizaraghi memperlihatkan sebuah … [+1207 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Ichsan Suhendra",
            "title": "Henidar Amroe Menangis, Cerita Tentang Kehidupannya Saat Ini",
            "description": "Berita Henidar Amroe Menangis, Cerita Tentang Kehidupannya Saat Ini terbaru hari ini 2022-05-12 06:31:18 dari sumber yang terpercaya",
            "url": "https://www.viva.co.id/showbiz/gosip/1474270-henidar-amroe-menangis-cerita-tentang-kehidupannya-saat-ini",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2022/05/12/627c407b17192-henidar-amroe_665_374.jpg",
            "publishedAt": "2022-05-11T23:31:18Z",
            "content": "VIVA  Nama Henidar Amroe pernah mengisi layar lebar dan televisi sejak tahun 1980an. Berbagai peran telah dilaluinya. Ia juga sudah mengantongi banyak penghargaan, salah satunya Pemeran Pendukung Wan… [+1113 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Dedi",
            "title": "5 Hewan Menyeramkan yang Memiliki Racun, Bisa Sembuhkan Penyakit AIDS?",
            "description": "Di dunia ini ada begitu banyak hewan menyeramkan yang menghasilkan racun. Fungsi utama dari racun tersebut adalah sebagai pertahanan diri atau untuk mencari mangsa.",
            "url": "https://www.viva.co.id/gaya-hidup/kesehatan-intim/1475725-5-hewan-menyeramkan-yang-memiliki-racun-bisa-sembuhkan-penyakit-aids",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2018/11/28/5bfe4773d3f8e-sejumlah-ikan-badut-amphiprioninae-berada-di-sekitar-anemon-actiniaria-yang_665_374.jpg",
            "publishedAt": "2022-05-17T07:31:42Z",
            "content": "VIVA  Di dunia ini ada begitu banyak hewan menyeramkan yang menghasilkan racun. Fungsi utama dari racun tersebut adalah sebagai pertahanan diri atau membantu hewan yang bersangkutan untuk mendapatkan… [+1133 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Sumiyati",
            "title": "Awas 4 Penyakit Ini Ditimbulkan dari Bulu Anabul, Begini Cegahnya",
            "description": "Memelihara anak bulu atau anabul seperti kucing dan anjing, bukan tidak ada risikonya bagi kesehatan. Bulu-bulu dari hewan ini bisa menimbulkan penyakit.",
            "url": "https://www.viva.co.id/gaya-hidup/kesehatan-intim/1477839-awas-4-penyakit-ini-ditimbulkan-dari-bulu-anabul-begini-cegahnya",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2020/11/12/5face1c461ead-ilustrasi-hewan-peliharaan-anjing_665_374.jpg",
            "publishedAt": "2022-05-24T00:00:03Z",
            "content": "VIVA  Kucing dan anjing adalah hewan yang sering dijadikan hewan peliharaan. Tingkahnya yang menggemaskan membuat banyak orang ingin sekali memelihara dan merawatnya. \r\nNamun, memelihara anak bulu at… [+1109 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Dedi",
            "title": "7 Artis Terkenal yang Memiliki Rumah di dalam Gang Senggol",
            "description": "Ada beberapa artis Tanah Air yang mempunyai rumah di dalam gang senggol. Mereka tampaknya lebih mengutamakan soal kenyamanan ketimbang kemewahan.",
            "url": "https://www.viva.co.id/showbiz/gosip/1476976-7-artis-terkenal-yang-memiliki-rumah-di-dalam-gang-senggol",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2022/05/02/62700b1cbfda5-keluarga-ayu-ting-ting_665_374.jpg",
            "publishedAt": "2022-05-20T22:00:04Z",
            "content": "VIVA  Beberapa selebriti tanah air memilih untuk hidup sederhana dan menomorduakan soal gengsi. Bahkan, walaupun mempunyai penghasilan yang cukup fantastis, beberapa di antara mereka justru lebih men… [+1125 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Jeffry Yanto Sudibyo",
            "title": "Gak Berharga! Pemilik Mobil Pajero Sport Bisa Nangis Melihat Ini",
            "description": "Pemilik mobil Pajero Sport bisa ngenes, sedih, atau menangis melihat mobil yang dibanggakannya hanya untuk mengangkut rumput oleh salah satu pria di sebuah desa.",
            "url": "https://www.viva.co.id/otomotif/mobil/1480681-gak-berharga-pemilik-mobil-pajero-sport-bisa-nangis-melihat-ini",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2018/12/20/5c1b3a0e295ba-mitsubishi-pajero-sport-saat-mengikuti-ekspedisi-tol-trans-jawa-2018_665_374.jpeg",
            "publishedAt": "2022-06-02T03:31:01Z",
            "content": "VIVA  Setiap pabrikan membuat mobil sesuai fungsi, dan peruntukannya. Jika dibutuhkan untuk membawa barang, atau hasil pertanian tentu jenis komersial seperti pikap, dan double cabin menjadi pilihan … [+1056 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Tamara Amalia",
            "title": "Memelihara Kucing Menyebabkan Kemandulan? Berikut Penjelasannya",
            "description": "Berita Memelihara Kucing Menyebabkan Kemandulan? Berikut Penjelasannya terbaru hari ini 2022-06-08 10:05:16 dari sumber yang terpercaya",
            "url": "https://www.viva.co.id/gaya-hidup/kesehatan-intim/1482757-memelihara-kucing-menyebabkan-kemandulan-berikut-penjelasannya",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2022/05/20/628677a957083-kucing-russian-blue_665_374.jpg",
            "publishedAt": "2022-06-08T03:05:16Z",
            "content": "VIVA  Kucing menjadi hewan peliharan yang cukup populer. Kegemasan dan kelucuan fisik dan tingkahnya membuat hewan berkaki empat ini digandrungi oleh banyak orang. Tak hanya sebagai peliharaan, bahka… [+1532 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Bayu Nugraha",
            "title": "Serang Ribuan Hewan Ternak Jatim, Kenali Ciri-ciri PMK",
            "description": "Penyakit Mulut dan Kuku (PMK) menyerang sedikitnya 3.481 hewan ternak di Jawa Timur. Satgas Pangan pun menyosialisasikan ciri-ciri, gejala dan cara menanganinya.",
            "url": "https://www.viva.co.id/berita/nasional/1474501-serang-ribuan-hewan-ternak-jatim-kenali-ciri-ciri-pmk",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2022/05/10/6279cf0b8ad71-satgas-pangan-polda-jatim-mengantisipasi-penyebaran-virus-pmk-pada-hewan-ternak_665_374.jpeg",
            "publishedAt": "2022-05-12T09:52:40Z",
            "content": "VIVA  Penyakit Mulut dan Kuku (PMK) menyerang sedikitnya 3.481 hewanternak di Jawa Timur. Namun begitu, masyarakat tidak perlu panik. Satuan Tugas Pangan Kepolisian Daerah Jatim pun langsung berkoord… [+2094 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Viva.co.id"
            },
            "author": "Agus Rahmat",
            "title": "Bobby Keluarkan Edaran Larangan Jual Beli Daging Anjing di Medan",
            "description": "Larangan jual beli daging anjing berdasarkan Surat Edaran (SE) Nomor 440/4676, yang ditandatangani oleh Wali Kota Medan Muhammad Bobby Afif Nasution.",
            "url": "https://www.viva.co.id/berita/nasional/1482116-bobby-keluarkan-edaran-larangan-jual-beli-daging-anjing-di-medan",
            "urlToImage": "https://thumb.viva.co.id/media/frontend/thumbs3/2022/06/06/629dd77cecd33-kepala-dinas-ketahanan-pangan-kota-medan-emilia-lubis-tengah_665_374.jpg",
            "publishedAt": "2022-06-06T10:38:02Z",
            "content": "VIVA  Di Kota Medan, Sumatera Utara, dilarang menjual beli daging anjing secara komersil. Hal tersebut tertuang dalam Surat Edaran (SE) Nomor 440/4676, yang ditandatangani oleh Wali Kota Medan Muhamm… [+779 chars]"
        },
        {
            "source": {
                "id": null,
                "name": "Detik.com"
            },
            "author": "Mochammad Fajar Nur",
            "title": "Dokter Hewan Ceritakan Rasanya Tertular Cacar Monyet",
            "description": "Cacar monyet ramai jadi sorotan, kasus mulai menyebar ke banyak negara, beberapa diidentifikasi di layanan kesehatan seks. Ini pengakuan dokter yang tertular.",
            "url": "https://health.detik.com/berita-detikhealth/d-6095049/dokter-hewan-ceritakan-rasanya-tertular-cacar-monyet",
            "urlToImage": "https://awsimages.detik.net.id/api/wm/2021/01/03/disease-x-1_169.jpeg?wid=54&w=650&v=1&t=jpeg",
            "publishedAt": "2022-05-25T12:11:03Z",
            "content": "Jakarta - Seorang dokter hewan bernama dr Kurt Zaeske sempat terinfeksi cacar monyet akibat menangani salah satu anjing peliharaan kliennya. Ia salah satu dari 47 orang yang terkena cacar monyet yang… [+1217 chars]"
        }
    ]
}
```

## Contributor

1. Danny Darmawan Putra       (C7115g1451)
2. Aziz Abdillah              (C2115g1452)
