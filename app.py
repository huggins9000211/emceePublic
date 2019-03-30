from flask import Flask, redirect, request
from flask import render_template

from quickbooks import *
from quickbooks.objects import *
from quickbooks.tools import *

from custlist import custlist, names_weekly, names_summer
from functions import lookup, get_totals, lookup_repair, lookup_summer, get_pre_used
from forms import *
from functions import resultss, prices, costTotals



app = Flask(__name__)
app.config['SECRET_KEY'] = 'Hidden'

callback_url = 'http://localhost:5000/extra'
QUICKBOOKS_CLIENT_ID = "Hidden"
QUICKBOOKS_CLIENT_SECRET = "Hidden"
realmid = ""
access_token = ""
password = "Hidden"
selected_name = ""
d1 = ""
d2 = ""
weekly_count = 0

session_manager = Oauth2SessionManager(
    sandbox=True,
    client_id=QUICKBOOKS_CLIENT_ID,
    client_secret=QUICKBOOKS_CLIENT_SECRET,
    base_url=callback_url
)

authorize_url = session_manager.get_authorize_url(callback_url)


@app.route('/', methods=['GET', 'POST'])
def connect():
    return redirect(authorize_url)


@app.route('/extra', methods=['GET', 'POST'])
def extra():
    session_manager.get_access_tokens(request.args.get("code"))
    global access_token
    global realmid
    global refresh_token
    access_token = session_manager.access_token
    refresh_token = session_manager.refresh_token
    realmid = request.args.get("realmId")
    print(realmid)
    session_manager.client_id = realmid
    print(vars(session_manager))
    return render_template("login.html")



@app.route('/login', methods=["POST"])
def login():
    enterd = request.form.get('password')
    form = SearchForm()
    if enterd == password:
        return render_template("form.html", form=form)
    return render_template("login.html")


@app.route("/results", methods=["GET", "POST"])
def results():
    print(request.referrer)
    if request.referrer == "http://localhost:5000/login":
        global weekly_count
        global selected_name
        global d1
        global d2
        notes = []
        form = EditForm()
        form2 = SearchForm()
        name = form2.myInput.data
        selected_name = name
        d1 = form2.startdate.data
        d2 = form2.enddate.data
        searchType = form2.searchType.data
        if searchType == "weekly":
            if d1 == "" or d2 == "":
                entries, num_weeks = lookup(name)
                weekly_count = num_weeks
                for entry in entries:
                    if entry["notes"] != "none":
                        notes.append({entry["date"]: entry["notes"]})
            else:
                entries, num_weeks = lookup(name, d1, d2)
                weekly_count = num_weeks
                print("Weekly count:")
                print(weekly_count)
                for entry in entries:
                    if entry["notes"] != "none":
                        notes.append({"date": entry["date"], "note": entry["notes"]})

            totals = get_totals(entries)
            print(costTotals)
            return render_template("results.html", name=name, entries=entries, form=form,
                                   totals=totals, notes=notes, i=0)
        elif searchType == "repair":
            if d1 == "" or d2 == "":
                entries = lookup_repair(name)
                for entry in entries:
                    if entry["notes"] != "none":
                        notes.append({"date": entry["date"], "note": entry["notes"]})
                return render_template("repairResults.html", entries=entries, notes=notes)
            else:
                entries = lookup_repair(name)
                for entry in entries:
                    if entry["notes"] != "none":
                        notes.append({"date": entry["date"], "note": entry["notes"]})
                return render_template("repairResults.html", entries=entries, notes=notes)
        elif searchType == "summer":
            entries = lookup_summer(name)
            for entry in entries:
                if entry["notes"] != "none":
                    notes.append({"date": entry["date"], "note": entry["notes"]})
            return render_template("summerResults.html", entries=entries, notes=notes)
    return render_template("error.html")

@app.route("/edit", methods=["GET", "POST"])
def edit():
    if request.referrer == "http://localhost:5000/results":
        print(resultss)
        form = EditForm()
        d = form.date.data
        for entry in resultss:
            print(entry["date"])
            print(d)
            if entry["date"] == d:
                form2 = EditForm2(**entry)
                print("Test")
        return render_template("edit.html", form2=form2)
    return render_template("error.html")


@app.route("/done", methods=["GET", "POST"])
def done():
    print(resultss)
    if request.referrer == "http://localhost:5000/results":
        print("Weekly count:")
        print(weekly_count)
        global selected_name
        #global weekly
        #global prepay
        global remaining
        global needs_billing


        print(selected_name)
        #new_session_manager = Oauth2SessionManager(
        #    client_id=realmid,
        #    client_secret=QUICKBOOKS_CLIENT_SECRET,
        #    access_token=access_token
        #)
        chemIdName = {}
        custIdName = {}
        print(resultss)
        qbclient = QuickBooks(
            sandbox=True,
            session_manager=session_manager,
            company_id=realmid
        )
        customers = Customer.all(qb=qbclient)
        items = Item.all(qb=qbclient)
        print(customers)
        for customerr in customers:
            print(vars(customerr))
            email = getattr(customerr, "PrimaryEmailAddr")
            name = getattr(customerr, "DisplayName")
            nameId = getattr(customerr, "Id")
            custIdName[name] = nameId
        print(custIdName)
        for item in items:
            print(vars(item))
            chem_name = getattr(item, "Name")
            chem_id = getattr(item, "Id")
            sub = getattr(item, "SubItem")
            if chem_name == "Weekly maintenance":
                global weekly

                weekly = item
            if chem_name == "Pre-pay season":
                global prepay
                print("defined")
                prepay = item
            if sub:
                chemIdName[chem_name] = chem_id
        print(chemIdName)
        selected_cust = Customer.get(custIdName[selected_name], qb=qbclient)
        cust_ref = selected_cust.to_ref()
        invoice = Invoice()
        invoice.CustomerRef = cust_ref
        invoice.CustomerMemo = CustomerMemo()
        #invoice.CustomerMemo.value = "Test customer memo"
        #invoice.PrivateNote = "Test private note"
        #invoice.BillEmail = EmailAddress()
        #invoice.BillEmail.Address = getattr(selected_cust, "PrimaryEmailAddr")
        x = 1
        for chem, ammount_used in costTotals.items():
            if selected_name == "Golden Little Elephant 16" or selected_name == "Golden Little Elephant 35":
                break
            if selected_name == "Golden Little Elephant 39":
                break
            chem_item = Item.get(chemIdName[chem], qb=qbclient)
            unit_price = getattr(chem_item, "UnitPrice")
            print(unit_price)
            print(ammount_used)
            item_ref = chem_item.to_ref()

            line = SalesItemLine()
            line.LineNum = x
            line.Amount = unit_price * ammount_used
            line.Description = getattr(chem_item, "Description")

            line.SalesItemLineDetail = SalesItemLineDetail()
            line.SalesItemLineDetail.ItemRef = item_ref
            line.SalesItemLineDetail.UnitPrice = unit_price
            line.SalesItemLineDetail.Qty = ammount_used
            line.SalesItemLineDetail.ServiceDate = d2
            invoice.Line.append(line)
            x += 1

        prepay_check = getattr(selected_cust, "CompanyName")
        if prepay_check == "PP Season":
            line = SalesItemLine()
            line.LineNum = x
            line.Amount = 0
            print("ressult len: {}".format(len(resultss)))
            remaining = get_pre_used(selected_name, len(resultss))
            needs_billing = abs(remaining)
            if remaining < 0:
                line.Description = "You are all out of your pre-paid weeks. " \
                                   "You will be charged for {} week of maintenance".format(needs_billing)
                x += 1
            else:
                line.Description = "Pre paid 16 weeks. {} weeks used form {}to{}. {} weeks remain"\
                     .format(len(resultss), d1, d2, remaining)

            line.SalesItemLineDetail = SalesItemLineDetail()
            weekly_ref = prepay.to_ref()
            line.SalesItemLineDetail.ItemRef = weekly_ref
            line.SalesItemLineDetail.UnitPrice = 0
            line.SalesItemLineDetail.Qty = weekly_count
            line.SalesItemLineDetail.ServiceDate = d2
            invoice.Line.append(line)
        else:
            line = SalesItemLine()
            line.LineNum = x
            line.Amount = names_weekly[selected_name] * weekly_count
            line.Description = "Weekly maintenance {} to {}".format(d1, d2)

            line.SalesItemLineDetail = SalesItemLineDetail()
            weekly_ref = weekly.to_ref()
            line.SalesItemLineDetail.ItemRef = weekly_ref
            line.SalesItemLineDetail.UnitPrice = names_weekly[selected_name]
            line.SalesItemLineDetail.Qty = weekly_count
            line.SalesItemLineDetail.ServiceDate = d2
            invoice.Line.append(line)

        if prepay_check == "PP Season":
            if remaining < 0:
                line = SalesItemLine()
                line.LineNum = x
                line.Amount = names_weekly[selected_name] * needs_billing
                line.Description = "Weekly maintenance".format(d1, d2)

                line.SalesItemLineDetail = SalesItemLineDetail()
                weekly_ref = weekly.to_ref()
                line.SalesItemLineDetail.ItemRef = weekly_ref
                line.SalesItemLineDetail.UnitPrice = names_weekly[selected_name]
                line.SalesItemLineDetail.Qty = needs_billing
                line.SalesItemLineDetail.ServiceDate = d2
                invoice.Line.append(line)

        invoice.BillEmail = getattr(selected_cust, "PrimaryEmailAddr")
        invoice.save(qb=qbclient)

        return render_template("done.html")
    return render_template("error.html")


@app.route("/invoice_summer", methods=["GET", "POST"])
def invoice_summer():

    if request.referrer == "http://localhost:5000/results":
        global selected_name
        global weekly
        global prepay
        print(selected_name)
        #new_session_manager = Oauth2SessionManager(
        #    client_id=realmid,
        #    client_secret=QUICKBOOKS_CLIENT_SECRET,
        #    access_token=access_token
        #)
        chemIdName = {}
        custIdName = {}
        print(resultss)
        qbclient = QuickBooks(
            sandbox=True,
            session_manager=session_manager,
            company_id=realmid
        )
        customers = Customer.all(qb=qbclient)
        items = Item.all(qb=qbclient)
        print(customers)
        for customerr in customers:
            print(vars(customerr))
            email = getattr(customerr, "PrimaryEmailAddr")
            name = getattr(customerr, "DisplayName")
            nameId = getattr(customerr, "Id")
            custIdName[name] = nameId
        print(custIdName)
        for item in items:
            print(vars(item))
            chem_name = getattr(item, "Name")
            chem_id = getattr(item, "Id")
            sub = getattr(item, "SubItem")
            #if chem_name == "Weekly maintenance":
             #   global weekly
             #   weekly = item
            if chem_name == "Prepay Summerizing":
                global prepay
                prepay = item
            #if sub:
                #chemIdName[chem_name] = chem_id
        print(chemIdName)
        selected_cust = Customer.get(custIdName[selected_name], qb=qbclient)
        cust_ref = selected_cust.to_ref()
        invoice = Invoice()
        invoice.CustomerRef = cust_ref
        invoice.CustomerMemo = CustomerMemo()
        #invoice.CustomerMemo.value = "Test customer memo"
        #invoice.PrivateNote = "Test private note"
        #invoice.BillEmail = EmailAddress()
        #invoice.BillEmail.Address = getattr(selected_cust, "PrimaryEmailAddr")
        x = 1

        prepay_check = getattr(selected_cust, "CompanyName")
        if prepay_check == "PP Sum":
            line = SalesItemLine()
            line.LineNum = x
            line.Amount = 0
            line.Description = "Summarizing pre paid"

            line.SalesItemLineDetail = SalesItemLineDetail()
            weekly_ref = prepay.to_ref()
            line.SalesItemLineDetail.ItemRef = weekly_ref
            line.SalesItemLineDetail.UnitPrice = 0
            line.SalesItemLineDetail.Qty = 1
            line.SalesItemLineDetail.ServiceDate = d2
            invoice.Line.append(line)

        else:
            line = SalesItemLine()
            line.LineNum = x
            line.Amount = names_summer[selected_name]
            line.Description = "Summarizing"

            line.SalesItemLineDetail = SalesItemLineDetail()
            weekly_ref = weekly.to_ref()
            line.SalesItemLineDetail.ItemRef = weekly_ref
            line.SalesItemLineDetail.UnitPrice = names_summer[selected_name]
            line.SalesItemLineDetail.Qty = 1
            line.SalesItemLineDetail.ServiceDate = d2
            invoice.Line.append(line)
        invoice.BillEmail = getattr(selected_cust, "PrimaryEmailAddr")
        invoice.save(qb=qbclient)

        return render_template("done.html")
    return render_template("error.html")


@app.route("/error", methods=["GET", "POST"])
def error():
    return render_template("error.html")


if __name__ == '__main__':
    app.run()
