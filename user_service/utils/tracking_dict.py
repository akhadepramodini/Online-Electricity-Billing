from user_service.utils.user_related_methods import get_user_dict_response


def tracking_dict(tracking_object):
    tracking_dict = {
        "Message": tracking_object.message,
        "Status": tracking_object.track_status
    }

    return tracking_dict