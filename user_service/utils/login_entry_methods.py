from user_service.utils.user_related_methods import get_user_dict_response


def get_dict_for_login_object(login_entry_object):
    response_dict = {"AuthID": login_entry_object.auth_token
                     }
    return response_dict