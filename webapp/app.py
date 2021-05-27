from flask import Flask, render_template, request

app = Flask(__name__)


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/process')
def process():
    id = request.args.get('id')
    price = request.args.get('price')
    date = request.args.get('date')
    updated = request.args.get('udate')
    sub = ""
    if (id == "0"):
        sub = "Chicken Tender"
    elif (id == "1"):
        sub = "Boar's Head Turkey"
    elif (id == "2"):
        sub = "Boar's Head Italian"
    elif (id == "3"):
        sub = "Boar's Head Ultimate"
    elif (id == "4"):
        sub = "Pub. Ultimate"
    elif (id == "5"):
        sub = "Boar's Head Ham"
    elif (id == "6"):
        sub = "Boar's Head Roast Beef"
    elif (id == "7"):
        sub = "Boar's Head Jerk Turkey & Gouda"
    elif (id == "8"):
        sub = "Chicken Cordon Bleu"
    elif (id == "9"):
        sub = "Boar's Head Maple Honey Turkey"
    elif (id == "10"):
        sub = "Boar's Head Philly Cheese"
    elif (id == "11"):
        sub = "Boar's Head Ham and Turkey"
    elif (id == "12"):
        sub = "Boar's Head Havana Bold"
    elif (id == "13"):
        sub = "Boar's Head Deluxe"
    elif (id == "14"):
        sub = "Boar's Head American"
    elif (id == "15"):
        sub = "Boar's Head EverRoast"
    elif (id == "16"):
        sub = "Boar's Head BLT"
    elif (id == "17"):
        sub = "Pub. Turkey"
    elif (id == "18"):
        sub = "Pub. Italian"
    elif (id == "19"):
        sub = "Pub. Ham"
    elif (id == "20"):
        sub = "Pub. Veggie"
    elif (id == "21"):
        sub = "Pub. Tuna Salad"
    elif (id == "22"):
        sub = "Pub. Roast Beef"
    elif (id == "23"):
        sub = "Pub. Philly Cheese"
    elif (id == "24"):
        sub = "Pub. Homestyle Beef Meatball"
    elif (id == "25"):
        sub = "Pub. Ham & Turkey"
    elif (id == "26"):
        sub = "Pub. Chicken Salad"
    elif (id == "27"):
        sub = "Pub. Mojo Pork"
    elif (id == "28"):
        sub = "Pub. Egg Salad"
    elif (id == "29"):
        sub = "Pub. Cuban"
    elif (id == "30"):
        sub = "Pub. Turkey Cranberry Holiday"
    elif (id == "31"):
        sub = "Boar's Head Southern BBQ Pitcraft Turkey"
    elif (id == "619"):
        sub = "619"

    else:
        sub = "Invalid ID for sub"
        price = "N/A"
        date = "N/A"

    return sub + ",-," + price + ",-," + date + ",-," + updated

if __name__ == '__main__':
    app.debug = True
    app.run()
