from flask import request, jsonify
from flask_restful import Resource

from user_service.db.user_models.models import Notification
from user_service.service_api_handlers import notification_handler
from user_service.utils import notification_utils
from user_service.utils.notification_utils import get_notifications


class Notification_history(Resource):
    def post(self):
        request_data=request.get_json()
        notification_objects=notification_handler.display_notification(request_data)
        print notification_objects
        response_dicts=[get_notifications(x)
                            for x in notification_objects]
        if response_dicts:

            print(jsonify({"Notification": response_dicts}))
            return jsonify({"Notification": response_dicts})
        else:
            return {"success": "NO notifications for you"}