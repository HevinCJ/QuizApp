package com.example.quizapp

object Constants {
    const val Username:String = "username"
    const val  TotalQuestions:String = "Totalquestions"
    const val CorrectAnswers:String = "correctanswers"


    fun getQuestions():ArrayList<Questions>{

        val questionlist = ArrayList<Questions>()

        val que1 = Questions(

            1,"which country's flag is this?",R.drawable.ic_flag_of_germany,"Argentina",
            "Australia","Germany","India",3

        )

        questionlist.add(que1)


        val que2 = Questions(

            2,"which country's flag is this?",R.drawable.ic_flag_of_brazil,"Argentina",
            "Australia","Germany","brazil",4

        )

        questionlist.add(que2)



        val que3 = Questions(

            3,"which country's flag is this?",R.drawable.ic_flag_of_india,"Argentina",
            "Beharin","kuwait","India",4

        )

        questionlist.add(que3)


        val que4 = Questions(

            4,"which country's flag is this?",R.drawable.ic_flag_of_new_zealand,"Argentina",
            "new zealand","Germany","India",2

        )

        questionlist.add(que4)


        val que5 = Questions(

            5,"which country's flag is this?",R.drawable.ic_flag_of_kuwait,"Nepal",
            "Australia","Germany","kuwait",4

        )

        questionlist.add(que5)


        val que6 = Questions(

            6,"which cartoon characters are this?", R.drawable.ictomandjerry,"Mouse and the friends",
            "Black cats","Oggy and cockroach","tom and jerry",4

        )

        questionlist.add(que6)


        val que7 = Questions(

            7,"Enter the CAPTCHA", R.drawable.iccaptcha,"qGphJD",
            "qGphlD","qGkhJD","qGpfJD",1

        )

        questionlist.add(que7)


        val que8 = Questions(

            8,"which animal is this?",R.drawable.iclion,"Zebra",
            "Cow","Lion","Deer",3

        )

        questionlist.add(que8)



        val que9 = Questions(

            9,"which animal is this?",R.drawable.ictiger,"Zebra",
            "Cow","Lion","tiger",4

        )
        questionlist.add(que9)


        return questionlist

    }

}