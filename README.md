# NorthPoleGame

Final project for the course Basic Programming (I0D41A) KU Leuven.

Santa is starting a town in the north pole because he needs more help making toys. The
land is barren and snowy all over to begin with. Eskimos move in or out and live inside igloos.
They work either in jacket making factories or Santa’s toy factory. The toy factories create more
revenue for the city, but without the jacket factories the citizens become very cold. There is a
danger for polar bear attacks which destroy buildings (except fishing holes), so there are ranger
stations that can be built to protect the citizens living in the vicinity of the ranger stations. The
citizens stay fed with the construction of fishing holes and they stay entertained with ice skating
rinks. Each building has a one-time building cost, monthly upkeep costs and tax revenue, which
are different depending on the building:

Building type / Building cost / Tax income / Upkeep costs

Igloo / 50000 / 1000 / 100

Fishing hole / 30000 / 1000 / 500

Ranger station / 150000 / 0 / 500

Ice skating rink / 50000 / 1000 / 900

Jacket factory / 1000000 / 4000 / 3900

Toy factory / 1000000 / 4500 / 3500

The factories are large (4 tiles), the ranger station is medium (2 tiles, horizontal) and
everything else is small (1 tile). You need to keep the needs of your population met if you want
the population to grow. The priorities for the well-being of the population are as follows:

1. Housing (1 igloo for every 5 people at least)
2. Food (1 fishing hole for every 20 people at least)
3. Safety (a ranger station for each 25 tiles)
4. Leisure (1 skating rink per 100 people at least)
5. Industry (places to work)

You begin by entering the dimensions of the land you want to make. Your budget is
$4,500,000 to begin with and your population is 100 people. Every turn represents 1 month of
time, during which the upkeep costs are subtracted from your budget, taxes are added, people
are subtracted, and buildings that are not near ranger stations may be destroyed (5% chance).
