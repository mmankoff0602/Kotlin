import java.text.NumberFormat
import java.util.*


fun main(args:Array<String>) {

	val scanner = Scanner(System.`in`)
	val currencyFormatter = NumberFormat.getCurrencyInstance()

// Create daily order counter/Create daily sales counter/Cal avg

	val stateTaxRate = 0.04
	val countyTaxRate = 0.02
	var orderCount = 0
	var dailyOrderAverage = 0.0


	// Welcome the user to the program
	println("""
		######################################
		#
		# Welcome to the Sales Tax Calculator!
		#
		# Press 0 if order is complete.
		#
		######################################
		
	""".trimIndent())

	/*
	 * Keep asking for more subtotals until the user chooses not to continue.
	 */
	while (true) {
		println("Please enter your subtotal:")
		val subTotal:Double = scanner.nextDouble()

		/*
		 * Exit the loop if we are done entering orders
		 * (The user enters a 0 for the subtotal)
		 */
		if (subTotal == 0.0) break


		// Calculations
		val stateTaxAmount:Double = stateTaxRate * subTotal
		val countyTaxAmount:Double = countyTaxRate * subTotal
		val taxTotal:Double = countyTaxAmount + stateTaxAmount
		val total:Double = subTotal + taxTotal

		/*
		 * Aggregates
		 * Average:
		 * 		average = total / count
		 * 		total = count * average
		 *		newAverage = (currentAverage * currentCount + newOrderTotal) / newCount
		 */
		orderCount += 1
		dailyOrderAverage = ((orderCount - 1) * dailyOrderAverage + subTotal) / orderCount


		// Results
		println("## Taxes ######")
		println("County: " + currencyFormatter.format(countyTaxAmount))
		println("State:  " + currencyFormatter.format(stateTaxAmount))
		println("Total Orders Made: $orderCount")
		println("Daily Order Average: ${currencyFormatter.format(dailyOrderAverage)}")
		println("## Total ######")
		println(currencyFormatter.format(total))

		/*
		 * If an order is greater than 400, say how big the order is.
		 * If an order is greater than 200, say its pretty nice.
		 * Otherwise, say nothing or that its a small order.
		 */
		when {
			total >= 400 -> println("Wow! That's a big order!")
			total >= 200 -> println("Cool, that's a decent order!")
			else -> println("Ugh, that's a small order!")
		}

	}

	println()
	println("Thanks for using the tax calculator!")
	println()

}
