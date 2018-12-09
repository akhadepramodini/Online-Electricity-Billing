from flask import request
from flask_restful import Resource

from user_service.db.user_models.models import LoginEntry


class Logout(Resource):
    def delete(self):
        auth_key = request.cookies.get('auth_key')
        print auth_key
        detele_object = LoginEntry.objects.get(auth_token=auth_key)
        detele_object.is_active = False
        detele_object.save()
        print "log out Successfully.....!"
