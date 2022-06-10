require('dotenv').config();

const express = require('express');
const res = require('express/lib/response');
const mysql = require('mysql');
const { isBuffer } = require('util');
const tf = require('@tensorflow/tfjs-node');
const app = express();
const path = require('path');
const vocab = require('./vocab/gejala.json')
const vocab1 = require('./vocab/penyakit.json')
const vocab2 = require('./vocab/kategori.json')
const vocab3 = require('./vocab/trans.json')
const axios = require('axios');
const { cos } = require('@tensorflow/tfjs-node');


let model = null;

app.use(express.json());
const port = process.env.PORT || 8080;
    
app.listen(port, () => {
    console.log(`berhasil ${port}`);
});


app.get("/", async (req, res) => {
    res.json({ status: "Success"});
});

app.post('/regis', async (req, res) => {
    if(Object.keys(req.body).length < 3 || Object.keys(req.body).length > 4){
        res.json({ status: "fail"});
    }else{
        const data = {
            email: req.body.email,
            password: req.body.password,
            role: "user",
            namaL: req.body.namaL,
            token: require('crypto').randomBytes(64).toString('hex')
        }
        const query = "INSERT INTO login VALUES (?, ?, ?, ?, ?)";
        pool.query(query, Object.values(data), (error) => {
            if(error) {
                res.json({ status: "Error Email" , reason: error.code});
            }else{
                res.json({status: "Success", data: data});
            }
        });
    }
});

app.post('/login', async (req, res) =>{

    if(Object.keys(req.body).length < 2 || Object.keys(req.body).length > 3){
        res.json({ status: "fail"});
    }else{
        const data = {
            email: req.body.email,
            password: req.body.password
        }
        const query = "SELECT * FROM login WHERE email = ? AND password = ?";
        pool.query(query, Object.values(data), (error,result) => {
            if(error) {
                res.json({ status: "fail" , reason: error.code});
            }else{
                res.json(result[0]);
            }
        });
    }
});

app.post('/login/checklogin', async (req, res) =>{
    const data = {
        token: req.body.token
    }
    const query = "SELECT * FROM login WHERE token = ?";
    pool.query(query, Object.values(data), (error,result) =>{
        if(error) {
            res.json({ status: "fail" , reason: error.code});
        }else{
            res.json(result[0]);
        }
    });
});

app.get('/vet', async (req, res) =>{
    const query = "SELECT * FROM vet_db";
    pool.query(query, (error,result) =>{
        if(error){
            res.json({status: "fail", reason: error.code});
        }else{

            let listvet = {vet: result};
            res.json(listvet);
        }
    });
});



function predictmodel(input, hewan){
    let nhewan = 0;
    if(hewan == "anjing"){
        nhewan = 1;
    }else if(hewan == "kucing"){
        nhewan = 2;
    }else if(hewan == "kelinci"){
        nhewan = 3;
    }

    var gejala = vocab;
    input.forEach(k =>{
        gejala[k] = 1;
    });
    let hasil = [];
    for(var i in gejala){
        hasil.push(gejala[i]);
    }
    
    hasil.push(nhewan);
    hasil = [hasil];
    return hasil;
}

app.post('/predict', async (req, res) =>{
    var hewan = req.body.hewan;
    var penyakit = req.body.penyakit;
    let listgejala = Object.keys(vocab);
    async function asynccall1(){
        let angka = [];
        let listgejalatrans = await someFunction(listgejala);
        penyakit.forEach(zyz => {
            angka.push(listgejalatrans.indexOf(zyz));

        })

        let hasilgejala = [];
        angka.forEach(ang => {
            hasilgejala.push(listgejala[ang])
        })


        try {
            if (!model) model = await tf.node.loadSavedModel(path.join(__dirname, '.','my_model'));
            let input = predictmodel(hasilgejala,hewan);

            const result = model.predict(tf.tensor(input));
            let arraypredict = await result.array();

            let max_predict = indexOfMax(arraypredict[0]);
            const sakit = Object.keys(vocab1);   
            let levelp = ['Major','Minor'];
            let hasillvel = '';
            levelp.forEach(level1 =>{
                vocab2[level1].forEach(level2 =>{
                    let gejala1 = level2.toString();
                    let gejala2 = sakit[max_predict].toString();
                    if(gejala2 == gejala1){
                        hasillvel = level1;
                    }
                })
            })
            let hasilperdict = await someFunction(sakit[max_predict]);
            return res.json({hasil: hasilperdict, kategori: hasillvel});
        } catch (e) {
            console.log(e);
            return res.json('error');
        }
    }
    asynccall1();
    
});


function indexOfMax(arr) {
    if (arr.length === 0) {
        return -1;
    }
  
    var max = arr[0];
    var maxIndex = 0;
  
    for (var i = 1; i < arr.length; i++) {
        if (arr[i] > max) {
            maxIndex = i;
            max = arr[i];
        }
    }
    return maxIndex - 1;
  }

app.get('/jadwalcek', async (req, res) => {

    const query = "SELECT vet_db.namaL,vet_db.number_hp,vet_db.email,service_schedules.service_id,service_schedules.location,service_schedules.day,service_schedules.open,service_schedules.close FROM service_schedules JOIN vet_db ON service_schedules.vet_id=vet_db.vet_id";
    pool.query(query, (error,result) =>{
        if(error){
            res.json({status: "fail", reason: error.code});
        }else{
            let jadwalcek = {jadwal: result};
            res.json(jadwalcek);
        }
    });
})

app.post('/appointment', async (req, res) => {
    const data = {
        id: 0,
        client_email: req.body.client_email,
        start_at: req.body.start_at,
        approved: 'waiting',
        message: req.body.message,
        service_id: req.body.service_id,
        vet_id: req.body.service_id
    }

    const query = "INSERT INTO appointment VALUES (?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?, ?, (SELECT vet_db.vet_id FROM vet_db JOIN service_schedules ON vet_db.vet_id = service_schedules.vet_id WHERE service_schedules.service_id = ?))";
    pool.query(query, Object.values(data), (error,result) => {
        if(error) {
            res.json({ status: "fail" , reason: error.code});
        }else{
            res.json({status: "Success", data: result});
        }
    });
})

app.get('/listgejala1', async (req, res) => {
    const gejala = Object.keys(vocab)
    const penyakit = Object.keys(vocab1)
    const data = {"gejala": gejala, "penyakit": penyakit};
    res.json(data)
})

app.get('/listgejala', async (req, res) => {
    
    async function asynccall(){
        const gejala = Object.keys(vocab)
        const hasiltrans = await someFunction(gejala)
        const penyakit = Object.keys(vocab1)
        const hasiltrans1 = await someFunction(penyakit)
        let angka = 0;
        let listtrans = {};
        let listtrans1 = {};
        hasiltrans.forEach(zxy => {
            listtrans[angka] = zxy;
            angka +=1;
        })
        angka = 0;
        hasiltrans1.forEach(zxy => {
            listtrans1[angka] = zxy;
            angka +=1;
        })

        const data = {"gejala": listtrans, "penyakit": listtrans1}
        res.json(data)
    }
    asynccall();
    
})

app.get('/listgejala2', async (req, res) =>{
    async function asynccall3(){
        let hasilakhir = {};
        let hasiltrans = [];
        let gejalatrans = Object.keys(vocab);
        let penyakittrans = Object.keys(vocab1);
        gejalatrans = await someFunction(gejalatrans);
        penyakittrans = await someFunction(penyakittrans);
        let angka = 1;
        gejalatrans.forEach(t =>{
            let gejalanih = {
            "id": angka,
            "nama_gejala": t
            }
            hasiltrans.push(gejalanih);
            angka +=1;
        })
        hasilakhir["gejala"] = hasiltrans
        angka = 1;
        hasiltrans = [];
        penyakittrans.forEach(t =>{
            let penyakitnih = {
            "id": angka,
            "nama_penyakit": t
            }
            hasiltrans.push(penyakitnih);
            angka +=1;
        })
        hasilakhir["penyakit"] = hasiltrans
        res.json(hasilakhir);
    }
    asynccall3();
})

app.post('/jadwalbook', async (req, res) => {

    const data = {
        client_email: req.body.client_email,
    }
    const query = "SELECT appointment.id,appointment.service_id,vet_db.namaL,vet_db.email,appointment.modified_at,appointment.start_at,appointment.approved,service_schedules.location,appointment.message FROM (((appointment JOIN login ON appointment.client_email = login.email) JOIN service_schedules ON appointment.service_id = service_schedules.service_id) JOIN vet_db ON appointment.vet_id = vet_db.vet_id) WHERE appointment.client_email = ?";
    pool.query(query, Object.values(data), (error,result) => {
        if(error) {
            res.json({ status: "fail" , reason: error.code});
        }else{
            res.json({status: "Success", data: result});
        }
    });
})

app.post('/edited', async (req, res) => {

    const data = {
        start_at: req.body.start_at,
        message: req.body.message,
        service_id: req.body.service_id,
        id: req.body.id
    }
    const query = "UPDATE appointment SET modified_at = CURRENT_TIMESTAMP, start_at = ?, message = ?, service_id = ? WHERE id = ?";
    pool.query(query, Object.values(data), (error,result) => {
        if(error) {
            res.json({ status: "fail" , reason: error.code});
        }else{
            res.json({status: "Success", data: result});
        }
    });
})

app.delete('/hapus/:id', async (req, res) => {

    const data = {
        id: req.params.id
    }
    
    const query = "DELETE FROM appointment WHERE id=?;"
    pool.query(query, Object.values(data), (error,result) => {
        if(error) {
            res.json({ status: "fail" , reason: error.code});
        }else{
            res.json({status: "Success", data: result});
        }
    });
})

app.post('/clientlist', async (req, res) => {
    const data = {
        vet_id: req.body.vet_id
    }
    const query = "SELECT appointment.id,appointment.client_email,appointment.modified_at,appointment.start_at,appointment.approved,appointment.message FROM appointment JOIN vet_db ON appointment.vet_id = vet_db.vet_id WHERE appointment.vet_id = ?"

    pool.query(query, Object.values(data), (error,result) => {
        if(error) {
            res.json({ status: "fail" , reason: error.code});
        }else{
            res.json({status: "Success", data: result});
        }
    })

})

app.put('/approve/:id', async (req, res) => {

    const data = {
        approved: req.body.approved,
        id: req.params.id
    }
    const query = "UPDATE appointment SET approved = ? WHERE id = ?";
    pool.query(query, Object.values(data), (error,result) => {
        if(error) {
            res.json({ status: "fail" , reason: error.code});
        }else{
            res.json({status: "Success", data: result});
        }
    });
})

let dictionary = [];

axios.get('https://newsapi.org/v2/everything?q=peliharaan&apiKey=f055bc71df734b84b83d42ec2fb0a63c')
  .then(res => {

    dictionary.push(res.data);

  })
  .catch(err => {
    console.log('Error: ', err.message);
  });

app.get('/articles', async (req, res) => {

    res.json(dictionary[0]);
})

const CREDENTIALS = JSON.parse(process.env.CREDENTIALS);

async function someFunction(text) {
    const {Translate} = require('@google-cloud/translate').v2;
    let hasiltranslate = [];
    const translate = new Translate({
        credentials: CREDENTIALS,
        projectId: CREDENTIALS.project_id
    });
    const target = 'id';
    let [translations] = await translate.translate(text, target);
    translations = Array.isArray(translations) ? translations : [translations];
    translations.forEach((translation, i) => {
        hasiltranslate.push(translation);
    });
    return new Promise((resolve, reject) => {
        setTimeout(()  => {resolve(hasiltranslate)}, 1000)
    });
}




// const TOKEN_ARG = process.env.TOKEN_TL;
// const tokenPath = process.argv[TOKEN_ARG];
// process.env.GOOGLE_APPLICATION_CREDENTIALS = tokenPath;

// const target = "id";

// async function translateText(text) {
//     let [translations] = await Translate.translate(text, target);
//     translations = Array.isArray(translations) ? translations : [translations];
//     return translations;
// };

  
const pool = mysql.createPool({
    user: process.env.DB_USERNAME,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    socketPath: `/cloudsql/${process.env.DB_PATH}`,
});