from flask import request, jsonify
from flask_restful import Resource

from user_service.service_api_handlers import history_post_handler, put_pay
from user_service.utils.history_dict_response import history_dict_response


class Bill_History(Resource):
    def post(self):
            request_data = request.get_json()
            history_object = history_post_handler.get_history(request_data)
            if history_object:
                return jsonify({"billing_history": history_dict_response(history_object)})
                #return {"result":"success"}
            else:
                return {"success": False}
    def get(self):
        auth_id = request.args.get('AuthID')
        history_objects = history_post_handler.get_history1(auth_id)
        if history_objects:
            response_dict=[history_dict_response(x)for x in history_objects]
            print response_dict
            return jsonify(
                {"billing_history": response_dict})
            # return {"result":"success"}
        else:
            print "hello"
            return {"success": False}



    def put(self):
            request_data=request.get_json()
            pay_object=put_pay.pay(request_data)
            if pay_object:
                return {"success":"paid"}
            else:
                return {"success": False}
