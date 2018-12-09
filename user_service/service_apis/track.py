from flask import request
from flask_restful import Resource

from user_service.db.user_models.models import Tracking, Connection
from user_service.service_api_handlers.tracking_handler import \
    put_tracking_handler, update_tracking
from user_service.utils.send_customerid_methods import send_tracking_id
from user_service.utils.tracking_dict import tracking_dict


class Track(Resource):
    def post(self):
        request_data=request.get_json()
        tracking_object=update_tracking(request_data)
        if tracking_object:
            return "success"
        else:
            return "failse"

    def put(self):
        request_data=request.get_json()
        tracking_object=put_tracking_handler(request_data)
        return {"result":tracking_dict(tracking_object)}
