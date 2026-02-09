package com.example.myapplication

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Quiz_Page : AppCompatActivity() {
    lateinit var countimer: CountDownTimer

    private var questions = arrayOf(
        "When was Malaysia formed?",
        "Who is the first Prime Minister of Malaysia?",
        "What is the largest mammal in the world?",
        "How many legs does a spider have?",
        "Who painted the Mona Lisa?",
        "Who wrote the play 'Romeo and Juliet'?",
        "Who invented the light bulb?",
        "Who is the author of 'Harry Potter'?",
        "Which European country has the largest population?",
        "Which country is known as the Land of the Rising Sun?",
        "Who is the composer that made 'In the Hall of the Mountain King?'",
        "Who had an album in the top ten in 2000 'Machina/The Machines of God'?",
        "Who is the lead singer of Arctic Monkeys?"
    )

    private var answer = arrayOf(
        "1963",
        "Tunku Abdul Rahman",
        "Whale",
        "Eight",
        "Leonardo da Vinci",
        "William Shakespeare",
        "Thomas Edison",
        "J.K. Rowling",
        "Russia",
        "Japan",
        "Edvard Grieg",
        "The Smashing Pumpkins",
        "Alex Turner"
    )

    private var options = arrayOf(
        "1963", //start
        "1962",
        "1960",
        "1965",
        "Abdul Razak Kussein", //5
        "Hussein Onn",
        "Tunku Abdul Rahman",
        "Abdullah Ahmad Badawi",
        "Shark", //9
        "Whale",
        "Elephant",
        "Dolphin",
        "Eight",
        "Ten",
        "Six",
        "Twelve",
        "Leonardo da Vinci",
        "Vincent Van Gogh",
        "Pablo Picasso",
        "Raphael",
        "William Shakespeare",
        "Henrik Ilbsen",
        "Oscar Wilde",
        "Tennessee Williams",
        "Thomas Edison",
        "Nikola Tesla",
        "Samuel F.B. Morse",
        "Otis Boykin",
        "J.K. Rowling",
        "Tom Clancy",
        "Stephen King",
        "Georges Simenon",
        "Germany",
        "United Kingdom",
        "Russia",
        "Italy",
        "United States",
        "Japan",
        "China",
        "South Korea",
        "Edvard Grieg",
        "Johann Strauss II",
        "Edward Elgar",
        "Gioachino Rossini",
        "The Smashing Pumpkins",
        "Oasis",
        "Coldplay",
        "Radiohead",
        "Alex Turner",
        "Frankie Valli",
        "Axl Rose",
        "Lou Reed"
    )

    public var streak = 0
    public var score: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_page)

        fun countdown() {
            val resultPage = Intent(this, EndResult_Page::class.java)
            val countdown = findViewById<TextView>(R.id.txtCountdown)
            val timer = intent.getStringExtra("Time")
            var countdownTimer: Long = 0

            when {
                timer.toString().toInt() == 10 -> countdownTimer = timer.toString().toLong()
                timer.toString().toInt() == 7 -> countdownTimer = timer.toString().toLong()
                timer.toString().toInt() == 5 -> countdownTimer = timer.toString().toLong()
            }

            countimer = object : CountDownTimer((countdownTimer * 1000) + 1000, 1000) {
                override fun onTick(p0: Long) {
                    countdown.text = "" + p0 / 1000
                }

                override fun onFinish() {
                    resultPage.putExtra("Streak", "$streak")
                    resultPage.putExtra("Score", "$score")

                    startActivity(resultPage)
                }
            }
        }
        
        fun showNextQuestion(): Int
        {
            val textQuestion = findViewById<TextView>(R.id.txtQuestion)
            val rdoBtnA = findViewById<Button>(R.id.button)
            val rdoBtnB = findViewById<Button>(R.id.button2)
            val rdoBtnC = findViewById<Button>(R.id.button3)
            val rdoBtnD = findViewById<Button>(R.id.button4)

            val questionPick = (0 until questions.size).random()
            textQuestion.text = questions[questionPick]
            val randomValue = arrayOfNulls<Int>(4)
            var x = 0

            val arrayShuffle = arrayOf<String>(options[questionPick*4], options[questionPick*4+1],
                options[questionPick*4+2], options[questionPick*4+3])
            arrayShuffle.shuffle()

            rdoBtnA.text = arrayShuffle[0]
            rdoBtnB.text = arrayShuffle[1]
            rdoBtnC.text = arrayShuffle[2]
            rdoBtnD.text =arrayShuffle[3]

            return questionPick
        }

        fun checkLives(i: Int)
        {
            var resultPage = Intent(this, EndResult_Page::class.java)
            val lives3 = findViewById<ImageView>(R.id.imgValueLives3)
            val lives2 = findViewById<ImageView>(R.id.imgValueLives2)
            val lives1 = findViewById<ImageView>(R.id.imgValueLives1)

            if(i == 2)
            {
                lives3.visibility = View.INVISIBLE
                lives2.visibility = View.VISIBLE
                lives1.visibility = View.VISIBLE
            }
            else if(i == 1)
            {
                lives3.visibility = View.INVISIBLE
                lives2.visibility = View.INVISIBLE
                lives1.visibility = View.VISIBLE
            }
            else
            {
                lives3.visibility = View.INVISIBLE
                lives2.visibility = View.INVISIBLE
                lives1.visibility = View.INVISIBLE
                countimer.cancel()
                resultPage.putExtra("Streak", "$streak")
                resultPage.putExtra("Score", "$score")
                startActivity(resultPage)
            }
        }


        fun checkQuestion(i: Int)
        {
            var resultPage = Intent(this, EndResult_Page::class.java)
            val i = showNextQuestion()
            val rdoBtnA = findViewById<Button>(R.id.button3)
            val rdoBtnB = findViewById<Button>(R.id.button2)
            val rdoBtnC = findViewById<Button>(R.id.button)
            val rdoBtnD = findViewById<Button>(R.id.button4)
            var textQuestion = findViewById<TextView>(R.id.txtQuestion)
            var lives = intent.getStringExtra("Lives").toString().toInt()
            val lives3 = findViewById<ImageView>(R.id.imgValueLives3)
            val lives2 = findViewById<ImageView>(R.id.imgValueLives2)
            val lives1 = findViewById<ImageView>(R.id.imgValueLives1)

            if(lives == 3)
            {
                lives3.visibility = View.VISIBLE
                lives2.visibility = View.VISIBLE
                lives1.visibility = View.VISIBLE
            }
            else
            {
                lives3.visibility = View.INVISIBLE
                lives2.visibility = View.INVISIBLE
                lives1.visibility = View.VISIBLE
            }

            countdown()
            countimer.start()

            val difficultyMultiplier = intent.getStringExtra("Difficulty")
            val timeMultiplier: Double

            when(intent.getStringExtra("Time"))
            {
                "10" -> timeMultiplier = 1.0
                "7" -> timeMultiplier = 1.5
                "5" -> timeMultiplier = 2.0
                else -> timeMultiplier = 0.0
            }

            val questionHeader = findViewById<TextView>(R.id.txtQuestionHeader)

            questionHeader.text = "Question " + (streak + 1) + ": "
            rdoBtnA.setOnClickListener {
                if(answer[i] == rdoBtnA.text)
                {
                    streak++
                    score += (100.0 * difficultyMultiplier.toString().toDouble() * timeMultiplier)
                    countimer.cancel()
                    checkQuestion(showNextQuestion())
                }
                else
                {
                    lives--
                    checkLives(lives)
                }
            }

            rdoBtnB.setOnClickListener {
                if(answer[i] == rdoBtnB.text)
                {
                    streak++
                    score += (100.0 * difficultyMultiplier.toString().toDouble() * timeMultiplier)
                    countimer.cancel()
                    checkQuestion(showNextQuestion())
                }
                else
                {
                    lives--
                    checkLives(lives)
                }
            }

            rdoBtnC.setOnClickListener {
                if(answer[i] == rdoBtnC.text)
                {
                    streak++
                    score += (100.0 * difficultyMultiplier.toString().toDouble() * timeMultiplier)
                    countimer.cancel()
                    checkQuestion(showNextQuestion())
                }
                else
                {
                    lives--
                    checkLives(lives)
                }
            }

            rdoBtnD.setOnClickListener {
                if(answer[i] == rdoBtnD.text)
                {
                    streak++
                    score += (100.0 * difficultyMultiplier.toString().toDouble() * timeMultiplier)
                    countimer.cancel()
                    checkQuestion(showNextQuestion())
                }
                else
                {
                    lives--
                    checkLives(lives)
                }
            }
        }

        checkQuestion(showNextQuestion())




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
