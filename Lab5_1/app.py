import json

from flask import Flask, request
from VaccineStorage import VaccineStorage
from Vaccine import Vaccine


class CustomEncoder(json.JSONEncoder):
    def default(self, o):
        return o.__dict__


app = Flask(__name__)
vaccine_storage = None


@app.route('/vaccines', methods=['GET'])
def get_vaccine_list():
    return json.dumps(vaccine_storage.get_list(), indent=4, cls=CustomEncoder)


@app.route('/vaccine', methods=['POST'])
def add_vaccine():
    print("New vaccine")

    request_data = request.get_json()
    title = request_data["title"]
    type = request_data["type"]
    id = request_data["id"]

    vaccine = Vaccine(id, type, title)
    vaccine_storage.add(vaccine)

    return json.dumps(vaccine, indent=4, cls=CustomEncoder)


vaccine_storage = VaccineStorage()
vaccine_storage.enrich_vaccines()
app.run()
