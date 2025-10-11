from django.contrib.auth.models import User
from django.db import models

# Create your models here.
class File(models.Model):
    # title = models.CharField()
    file = models.FileField(upload_to='files/')
    # uploaded_at = models.DateTimeField(auto_now_add=True)

class Task(models.Model):
    title = models.CharField()
    query = models.CharField()
    solution = models.CharField(max_length=200)
    solved = models.BooleanField()

    solvers = models.ManyToManyField(User)
    creator = models.ForeignKey(User,  on_delete=models.PROTECT, related_name='creator', null=True)
    confirmer = models.ForeignKey(User, on_delete=models.CASCADE, related_name='confirmer', null=True)
    pub_date = models.DateTimeField("date published")
    class Meta:
        ordering = ['title']

    def __str__(self):
        return (self.__class__.__name__ + " { " +
                    "query : " + self.query + " , " +
                    "solution : " + self.solution + " , " +
                    "solved : " + str(self.solved) +
                "\n } ")


# class File(models.Model):
# #         # id = models.OneToOneField(User)
#     filename = models.CharField()
#     file = models.FileField(upload_to='files/'),
#
#     def __str__(self):
#         return self.filename

class Text(models.Model):
    text = models.TextField()

    def __str__(self):
        return self.text


class ExternalUrl(models.Model):
    url = models.URLField()
    def __str__(self):
        return self.url

class TaskMultiPartData(models.Model):
    text = models.OneToOneField(Text, on_delete=models.CASCADE)
    url = models.ManyToManyField(ExternalUrl)
    file = models.ManyToManyField(File)


