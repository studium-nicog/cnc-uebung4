from flask import Flask, request, abort
import wikipedia, os

PORT = int(os.environ.get('PORT', '80')) # Config aus Env-Variable (Faktor 3)
LANG = os.environ.get('LANGUAGE', 'de')  # Config aus Env-Variable (Faktor 3)

app = Flask(__name__, static_folder="static")

@app.route("/")
def index():
    wiki = request.args.get('wiki', default="Nürnberg")
    try:
        wikipedia.set_lang(LANG)
        content = wikipedia.page(wiki).summary        
        return f"""<h1>{ wiki }</h1>
            Ich habe diese Zusammenfassung auf Wikipedia gefunden:
            <p>{ content }</p>
            """
    except:
        abort(404, f"{ wiki } not found")

print("Starting ...") # Log to stdout (Faktor 11)
app.run(host="0.0.0.0", port=PORT)