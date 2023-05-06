package Group.G19.mobliedevelopmentcw1

object Constants {
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val question1 = Question(
            1,
            "1+1=?",
            "1",
            "2",
            "3",
            "4",
            2
        )
        questionsList.add(question1)

        val question2 = Question(
            1,
            "2+2?",
            "1",
            "2",
            "3",
            "4",
            4
        )



        questionsList.add(question2)


        return questionsList
    }
}