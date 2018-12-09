from user_service.utils.user_related_methods import get_user_dict_response


def complaint_get_dict(complaint_object):


    response_dict = {
                     "title": complaint_object.title,
                     "complaint_text": complaint_object.complaint_text,
                     'status': complaint_object.status
                     }

    return response_dict