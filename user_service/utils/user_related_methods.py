from user_service.db.user_models.models import User
from user_service.utils import get_connection_dict_response
from user_service.utils.exceptions import UnauthorisedException


def get_user_dict_response(user_object):
    response_dict = {'username': user_object.username,
                     'customerID': get_connection_dict_response.get_connection_dict_response(
                         user_object.connection),
                     'phone': user_object.phone,
                     'isConfirm': user_object.is_confirmed
                     }

    return response_dict


def check_credentials_and_get_object(username, password):
    try:
        user_object = User.objects.get(username=username, password=password)
        print user_object.username
        return user_object
    except Exception as e:
        raise UnauthorisedException()
