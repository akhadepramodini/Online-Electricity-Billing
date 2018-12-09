# from user_service.db.user_models.models import User
# from user_service.utils.exceptions import NotFoundException
#
#
# def get_single_user(requeste_data):
#     username = requeste_data['username']
#     try:
#         user_object = User.objects.get(username=username)
#         print user_object
#         return user_object
#
#     except Exception as e:
#         raise NotFoundException("User not found!!")
#
#
# def get_all_users():
#     user_objects = User.objects.all()
#     return user_objects
