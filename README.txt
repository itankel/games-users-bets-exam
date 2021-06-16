
How to use this microservice:

1. To GET suspicious users made bets from different countries in the provided time period.

	/api/suspicious/location/bet-games/{startOn}/{endsOn}

	example:
	http://localhost:8100/api/suspicious/location/bet-games/2021-06-08%2000:00:00/2021-06-09%2000:00:00


2. To GET suspicious events which:
	Win/Bet ratio is higher than 1/10 in the provided time period.
	online time is higher than 5 hours in the provided time period.

	/api/suspicious/activity/bet-games/{startOn}/{endsOn}

	example:
	http://localhost:8100/api/suspicious/activity/bet-games/2021-06-08%2012:00:00/2021-06-09%2012:00:00


3. To GET statistics for all games by time period.

	/api/statistics/all/bet-games/{startOn}/{endsOn}

	example:
	http://localhost:8100/api/statistics/all/bet-games/2021-06-08%2000:00:00/2021-06-09%2000:00:00


4. To GET statistics for specific game by game name and time period.


	api/statistics/{gameName}/bet-games/{startOn}/{endsOn}

	example:
	http://localhost:8100/api/statistics/whist/bet-games/2021-06-08%2000:00:00/2021-06-09%2012:00:22