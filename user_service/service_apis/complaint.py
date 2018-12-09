from flask import request, jsonify
from flask_restful import Resource

from user_service import utils
from user_service.service_api_handlers import complaint_post_handler, complaint_get_handler
from user_service.utils.complaint_get_dict import complaint_get_dict


class Complaint(Resource):
    def post(self):
            request_data = request.get_json()
            complaint_object = complaint_post_handler.create_complaint(request_data)
            if complaint_object:
                return jsonify({"Complaint": "success"})

            else:
                return {"success": False}


    def put(self):
            request_data = request.get_json()
            complaint_object = complaint_post_handler.complaint_view(request_data)
            response_dicts = [complaint_get_dict(x)
                              for x in complaint_object]
            print response_dicts
            if response_dicts:
                return jsonify({"Complaint": response_dicts})

            else:
                return {"success": False}