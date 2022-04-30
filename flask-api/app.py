from flask import Flask
from flask_restx import Resource, Api
from router.prediction import api as ns1

app = Flask(__name__)
api = Api(app)

api.add_namespace(ns1, '/predictions')


@api.route('/hello/<string:name>')
class Hello(Resource):
    def get(self, name):
        return {"message": "Welcome, %s!" % name}


if __name__ == "__main__":
    app.run(debug=True, host='127.0.0.1', port=5000)
