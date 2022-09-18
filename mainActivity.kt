class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		button10.setOnClickListener {
			reset()

		}
	}
	var YouCount = 0
	var ComputerCount = 0
	fun clickfun(view:View)
	{
		if(playerTurn) {
			val but = view as Button
			var cellID = 0
			when (but.id) {
				R.id.button -> cellID = 1
				R.id.button2 -> cellID = 2
				R.id.button3 -> cellID = 3
				R.id.button4 -> cellID = 4
				R.id.button5 -> cellID = 5
				R.id.button6 -> cellID = 6
				R.id.button7 -> cellID = 7
				R.id.button8 -> cellID = 8
				R.id.button9 -> cellID = 9
			}
			playerTurn = false;
			Handler().postDelayed(Runnable { playerTurn = true } , 600)
			playnow(but, cellID)
		}
	}
	var You = ArrayList<Int>()
	var Computer = ArrayList<Int>()
	var emptyCells = ArrayList<Int>()
	var activeUser = 1
	fun playnow(buttonSelected:Button , currCell:Int)
	{ val audio = MediaPlayer.create(this , R.raw.poutch)
		if(activeUser == 1)
		{
			buttonSelected.text = "X"
			buttonSelected.setTextColor(Color.parseColor("#EC0C0C"))
			You.add(currCell)
			emptyCells.add(currCell)
			audio.start()
			buttonSelected.isEnabled = false
			Handler().postDelayed(Runnable { audio.release() } , 200)
			val checkWinner = checkwinner()
			if(checkWinner == 1){
				Handler().postDelayed(Runnable { reset() } , 2000)
			}
			else if(singleUser){
				Handler().postDelayed(Runnable { robot() } , 500)
			}
			else
				activeUser = 2
		}
		else
		{
			buttonSelected.text = "O"
			audio.start()
			buttonSelected.setTextColor(Color.parseColor("#D22BB804"))
			activeUser = 1
			Computer.add(currCell)
			emptyCells.add(currCell)
			Handler().postDelayed(Runnable { audio.release() } , 200)
			buttonSelected.isEnabled = false
			val checkWinner = checkwinner()
			if(checkWinner == 1)
				Handler().postDelayed(Runnable { reset() } , 4000)
		}
	}
	fun reset()
	{
		You.clear()
		Computer.clear()
		emptyCells.clear()
		activeUser = 1;
		for(i in 1..9)
		{
			var buttonselected : Button?
			buttonselected = when(i){
				1 -> button
				2 -> button2
				3 -> button3
				4 -> button4
				5 -> button5
				6 -> button6
				7 -> button7
				8 -> button8
				9 -> button9
				else -> {button}
			}
			buttonselected.isEnabled = true
			buttonselected.text = ""
			textView.text = "You : $YouCount"
			textView2.text = "Computer : $ComputerCount"
		}
	}
	fun disableReset()
	{
		button10.isEnabled = false
		Handler().postDelayed(Runnable { button10.isEnabled = true } , 2200)
	}
}