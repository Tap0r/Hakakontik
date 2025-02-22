import firebase_admin
from firebase_admin import credentials, db
import json

cred = credentials.Certificate("cred.json")
firebase_admin.initialize_app(cred, {
'databaseURL': 'https://hakaton-f8fc1-default-rtdb.europe-west1.firebasedatabase.app/'
})

ref = db.reference('')

users_ref = ref.child('olimp')
with open('olimp.json', 'r', encoding='utf-8') as file:
    data = json.load(file)  # загружаем из файла данные в словарь data
    res = users_ref.set(data)

# print(res)

# data = ref.get()
#
# print(data)