from Vaccine import Vaccine


class VaccineStorage:
    vaccines = []

    def __init__(self):
        print("Vaccine storage initialized")

    def enrich_vaccines(self) -> None:
        print("Enriching vaccines...")
        self.vaccines.append(Vaccine(1, "Грипп", "АнтиГрипп"))
        self.vaccines.append(Vaccine(2, "Грипп", "ПротивоГрипп"))
        self.vaccines.append(Vaccine(3, "Коровавирус", "Спутник"))
        self.vaccines.append(Vaccine(4, "Коровавирус", "Спутник Лайт"))
        print("Enriched successful!")

    def get_list(self) -> []:
        return self.vaccines

    def add(self, vaccine):
        self.vaccines.append(vaccine)
