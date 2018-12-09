from flask import request
from flask_restful import Resource

from user_service.db.user_models.models import Unanswered


class Admin(Resource):
    def post(self):
        request_data=request.get_json()
        question_id=request_data['question']
        question_object=Unanswered.objects.get(id=question_id)
        question_text=Unanswered.objects