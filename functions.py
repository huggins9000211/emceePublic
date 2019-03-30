from datetime import datetime, timedelta

import firebase_admin
from firebase_admin import credentials, firestore
import google.cloud.exceptions


cred = credentials.Certificate("hidden")
default_app = firebase_admin.initialize_app(cred)
db = firestore.client()

resultss = []
prices = {"Acid": 6.0, "Shock": 4.0, "Chlorine tablets": 1.5, "Salt": 10.0, "pH +": 1.0, "Yellowtrine": 15.0,
           "Clarifier": 4, "Calcium": 0}
costTotals = {}


#"Acid": 0,
       # "Shock": 0,
        #"Chlorine": 0,
       # "Salt": 0,
       # #"pH": 0,
        #"Yellowtrine": 0,
       # "Clarifier": 0,
      #  "Sodium": 0,
        #"Calcium": 0,
     #   "Conditioner": 0



def lookup(name, d1="0", d2="0"):
    global weeklycount
    weeklycount = 0
    entries = []
    resultss.clear()
    if d1 == "0" or d2 == "0":
        docs = db.collection(name)
        for doc in docs:
            entries.append(doc.to_dict())
            resultss.append(doc.to_dict())
            weeklycount += 1
    else:
        str_format = "%Y-%m-%d"
        date_range = d1 + ">" + d2
        test_dates = date_range.split(">")
        dates = []
        try:
            date_one = datetime.strptime(test_dates[0], str_format)
            date_two = datetime.strptime(test_dates[1], str_format)
        except ValueError:
            return "You enterd an invlaid date. Takeing you back to the search page"
        else:
            delta = date_two - date_one
            for days in range(delta.days + 1):
                temp_date = date_one + timedelta(days)
                temp_date_str = temp_date.strftime(str_format)
                dates.append(temp_date_str)


            for date in dates:
                doc_ref = db.collection(name).document(date)
                if doc_ref.get().exists:

                    doc = doc_ref.get()
                    entries.append(doc.to_dict())
                    resultss.append(doc.to_dict())
                    weeklycount += 1
            print(weeklycount)
            return entries, weeklycount


def lookup_repair(name, d1="0", d2="0"):
    resultss.clear()
    entries = []

    if d1 == "0" or d2 == "0":
        docs = db.collection(name).document("Repairs").collection("Repairs").get()
        for doc in docs:
            entries.append(doc.to_dict())
            resultss.append(doc.to_dict())

        return entries
    else:
        str_format = "%Y-%m-%d"
        date_range = d1 + ">" + d2
        test_dates = date_range.split(">")
        dates = []
        try:
            date_one = datetime.strptime(test_dates[0], str_format)
            date_two = datetime.strptime(test_dates[1], str_format)
        except ValueError:
            return "You enterd an invlaid date. Takeing you back to the search page"
        else:
            delta = date_two - date_one
            for days in range(delta.days + 1):
                temp_date = date_one + timedelta(days)
                temp_date_str = temp_date.strftime(str_format)
                dates.append(temp_date_str)

            for date in dates:
                doc_ref = db.collection(name).document("Repairs").collection("Repairs").document(date)
                if doc_ref.get().exists:
                    doc = doc_ref.get()
                    entries.append(doc.to_dict())
                    resultss.append(doc.to_dict())

            return entries



def lookup_summer(name):
    resultss.clear()

    entries = []
    docs = db.collection(name).document("Summer").collection("Summer").get()
    for doc in docs:
        entries.append(doc.to_dict())
        resultss.append(doc.to_dict())

    return entries

def get_totals(logs):
    costTotals.clear()
    totals ={
        "Acid": 0,
        "Shock": 0,
        "Chlorine": 0,
        "Salt": 0,
        "pH": 0,
        "Yellowtrine": 0,
        "Clarifier": 0,
        "Sodium": 0,
        "Calcium": 0,
        "Conditioner": 0


    }
    for log in logs:
        if int(log.get("acid")) > 0:
            totals["Acid"] += int(log.get("acid"))
        if int(log.get("shock")) > 0:
            totals["Shock"] += int(log.get("shock"))
        if int(log.get("chlorine")) > 0:
            totals["Chlorine"] += int(log.get("chlorine"))
        if int(log.get("salt")) > 0:
            totals["Salt"] += int(log.get("salt"))
        if int(log.get("ph")) > 0:
            totals["pH"] += int(log.get("ph"))
        if int(log.get("yellowtrine")) > 0:
            totals["Yellowtrine"] += int(log.get("yellowtrine"))
        if int(log.get("clarifier")) > 0:
            totals["Clarifier"] += int(log.get("clarifier"))
        if int(log.get("sodium")) > 0:
            totals["Sodium"] += int(log.get("sodium"))
        if int(log.get("calcium")) > 0:
            totals["Calcium"] += int(log.get("calcium"))
        if int(log.get("conditioner")) > 0:
            totals["Conditioner"] += int(log.get("conditioner"))

    for total in totals:
        if totals["Acid"] > 0:
            costTotals["Acid"] = totals["Acid"]
        if totals["Shock"] > 0:
            costTotals["Shock"] = totals["Shock"]
        if totals["Chlorine"] > 0:
            costTotals["Chlorine tablets"] = totals["Chlorine"]
        if totals["Salt"] > 0:
            costTotals["Salt"] = totals["Salt"]
        if totals["pH"] > 0:
            costTotals["pH +"] = totals["pH"]
        if totals["Yellowtrine"] > 0:
            costTotals["Yellowtrine"] = totals["Yellowtrine"]
        if totals["Clarifier"] > 0:
            costTotals["Clarifier"] = totals["Clarifier"]
        if totals["Sodium"] > 0:
            costTotals["Alkalinity Increaser"] = totals["Sodium"]
        if totals["Calcium"] > 0:
            costTotals["Calcium Hardness Increaser"] = totals["Calcium"]
        if totals["Conditioner"] > 0:
            costTotals["Chlorine Stabilizer / Conditioner"] = totals["Conditioner"]

    return totals

def get_pre_used(name, num_used):
    doc_ref = db.collection(name).document(u'info')

    doc = doc_ref.get()
    dic = doc.to_dict()
    used = dic["uses"]
    doc_ref.update({u'uses': used + num_used})
    return 16 - (used + num_used)

