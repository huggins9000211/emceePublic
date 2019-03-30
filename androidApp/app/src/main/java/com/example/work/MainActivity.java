package com.example.work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String[] CLIENTS = new String[]{
            "Aarons", "Ackerman", "Alexander", "Alfiero", "Altman", "Altman, Russell", "Ambriano"
            , "Amoruso", "Amoruso, Nick", "Angacian", "Assael", "Atkinson", "Auerbach", "Axelrod"
            , "Axelrod, Ira", "Baer", "Balemian, Laura", "Bandler", "Baran", "Barbour", "Beal",
            "Beberman", "Bell", "Bell, Nathalie", "Bennett", "Berger, Tricia", "Beys, Emily",
            "Bez", "Bez, Peter", "Bezmalinovic", "Bhansali", "Bharara", "Bienenfeld", "Bigger",
            "Bill", "Blank, Jamie", "Blank, Sean", "Borzooyeh", "Boulukos", "Bozek", "Brotman",
            "Buscaglia", "Callahan", "Capobianco", "Carino", "Carino, Sal", "Carlson", "Chang",
            "Checo", "Clark", "Clegg", "Cohen", "Cohen, Nati", "Cohen, Phyllis", "Cook", "Curry",
            "Curtis", "Davidson", "De Felice", "Decapite", "Del Balso", "Delasandro", "Delia",
            "Dell", "Dempsey", "Denner", "Deriso, Jamie", "Ding", "Dr.  Kalmar", "Dreher", "Duff"
            , "Ebert", "Ehrlich", "Fagin", "Feibl", "Feit", "Felix", "Fensterman", "Ferrara",
            "Fields", "Flanagan", "Fletcher", "Flowers, Larry", "Flynn", "Fox ", "Fox, Greta",
            "Friedman", "Gaing", "Gang", "Genovesi, Erum", "Gershowitz", "Ghamar", "Giacobbe",
            "Godlis", "Goldberg", "Golden Little Elephant 16", "Golden Little Elephant 35",
            "Golden Little Elephant 39", "Goldsmith, Tom", "Gordon, Maris", "Gottfried", "Goyal",
            "Grant", "Hafkin", "Halal", "Handler", "Hanover", "Haupt", "Hazan", "Heller", "Hentze"
            , "Herenstein", "Hirsch", "Hochwald", "Honig", "Hoover", "Horan", "Horsch", "Jackson"
            , "Jensen", "Kalmar", "Kamin, Lewis", "Kammer", "Kaplan", "Kaplan, Penny", "Kaplan, " +
            "Steven", "Katz", "Kazanchi", "Kelly, Michael and Mary Beth", "Kerker", "Kern",
            "Kilcullen, Peter", "Klein", "Klipper", "Korvar", "Kramer", "Kraulstein", "Laderer",
            "Lazar", "Lebowitz", "Leeds, Liz", "Lerner", "Levine", "Levitt", "Lindstrom, Tina",
            "Litman", "Locker", "Loeb", "Lucas", "Macarone", "Macchiarella", "Mahlotra", "Maltin"
            , "Manino", "Marsalise", "Martin, Tim", "McDonagh", "McTygue", "Meirowitz", "Mevorah," +
            " Mindy", "Michaels", "Michelman", "Mohtadi", "Monaco", "Mortarotti", "Mosleh",
            "Mosleh, Bita", "Moslow", "Nahas", "Nesi", "Nierenberg", "Nissan", "Nordstrom", "O" +
            "'Connor", "Oliver", "Ospina", "Parga", "Park", "Pasqualone", "Passarelli", "Pelen " +
            "Enterprises, Inc.", "Perlmutter", "Pollaci, Joseph", "Portanese", "Rao", "Rapp",
            "Reynolds", "Rieders", "Riley", "Rispler", "Robinson", "Roeske", "Romanoff",
            "Rosenberg", "Ross", "Rueb", "Sabatino", "Samide", "Sangha, Rummy", "Schaefer",
            "Schlanger", "Schneider", "Schneider, Michelle", "Sciarrino", "Serel", "Sessa",
            "Shaber", "Shaber, Debbie", "Sharf", "Sher, Gary", "Sherman, Wendy", "Sica", "Siegel"
            , "Siegel, Andrea", "Siegel, Howard", "Simon", "Sirignano", "Siskind", "Skolnick",
            "Smith", "Solomon", "Steigman", "Stein", "Stone", "Su", "Suneja", "Sunshine Pools",
            "Talamas, Michael", "Tallarico", "Taxel", "Thomas", "Tiberio", "Torres", "Tsitsoulas"
            , "Vahamonde", "Vandenberg, Nicole", "Wank", "Wasserman", "Wechsler", "Weingard",
            "Weiss, Kevin and Kelly", "Werfel", "Widmer", "Wittels", "Yan", "Zachary", "Zelentiz"
            , "Zhang, Donna"
    };

    public static String empName;
    public static String custName;


    Button nextButt;

    EditText nameIn;
    AutoCompleteTextView custNameIn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameIn = (EditText) findViewById(R.id.nameIn);
        custNameIn = findViewById(R.id.custNameIn);
        nextButt = (Button) findViewById(R.id.nextButt);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, CLIENTS);
        custNameIn.setAdapter(adapter);
        nextButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                empName = nameIn.getText().toString();
                custName = custNameIn.getText().toString();

                Intent intent = new Intent(MainActivity.this, AdressTest.class);
                startActivity(intent);


            }
        });
    }
}

