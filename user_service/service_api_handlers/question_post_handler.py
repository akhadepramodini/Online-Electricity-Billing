from user_service.db.user_models.models import Question


def create_question(request_data,user_object):

    question_string = request_data['questionString']

    try:
        question_object = Question.objects.create(user=user_object,question_string=question_string)
        return question_object
    except Exception as e:
        print e
        return None

def verifie(request_data):
    question_string = request_data['questionString']
    try:
        question_object=Question.objects.get(question_string=question_string)
        return question_object
    except Exception as e:
        print e
        return None