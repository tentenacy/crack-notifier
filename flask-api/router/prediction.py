from flask import Flask, request
from flask_restx import Resource, Api, Namespace
from werkzeug.datastructures import FileStorage

import numpy as np
import os
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing import image


app = Flask(__name__)

api = Namespace('Prediction')
upload_parser = Api(app).parser()
upload_parser.add_argument('file', location='files',
                           type=FileStorage, required=True)


def load_image(img_path):

    img = image.load_img(img_path, target_size=(100, 100))
    img_tensor = image.img_to_array(img)                    # (height, width, channels)
    img_tensor = np.expand_dims(img_tensor, axis=0)         # (1, height, width, channels), add a dimension because the model expects this shape: (batch_size, height, width, channels)

    return img_tensor


model = load_model('./ml-models/2022_04_30_12_46_19')


@api.route('')
class Prediction(Resource):
    def post(self):
        args = upload_parser.parse_args()
        uploaded_file = args['file']  # This is FileStorage instance
        uploaded_file.save('file.jpg')

        loaded_image = load_image('./file.jpg')

        pred = model.predict(loaded_image)

        os.remove('./file.jpg')

        return {'accuracy': round(pred[0][1] * 100, 4)}


