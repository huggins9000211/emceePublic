from flask_wtf import FlaskForm
from wtforms import StringField, DateField, RadioField


class SearchForm(FlaskForm):
    startdate = StringField("Start date:")
    enddate = StringField("End date:")
    myInput = StringField("Customer name:", id="myInput")
    searchType = RadioField('Search type', choices=[('summer', 'Summerizing'), ('weekly', 'Weekly Service'),
                                                 ('repair', 'Repair')])


class LoginForm(FlaskForm):
    password = StringField("Password:")


class EditForm(FlaskForm):
    date = StringField("date")


class EditForm2(FlaskForm):
    acid = StringField("acid")
    shock = StringField("shock")
    chlorine = StringField("chlorine")
    salt = StringField("salt")
    ph = StringField("ph")
    yellowtrine = StringField("yellowtrine")
    clarifier = StringField("clarifier")
    sodium = StringField("sodium")
    calcium = StringField("calcium")
    conditioner = StringField("conditioner")
