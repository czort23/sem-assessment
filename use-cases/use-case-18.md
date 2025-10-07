# USE CASE: 18 All the capital cities in a continent organised by largest population to smallest.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of all the cities in the continent organised by largest population to smallest.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city, country records with correct links between country and capital city, as well as continent relationship.

### Success End Condition

System generates report and displays a list of all capital cities within the selected continent ordered from largest to the smallest.

### Failed End Condition

User enters invalid continent name.
If query fails or data unavailable error is displayed.

### Primary Actor

Organisation.

### Trigger

"City Reports --> Capital Cities by Continent" is selected.
User specifies continent name.

## MAIN SUCCESS SCENARIO

1. User selects navigates to City Reports --> Capital Cities by Continent.
2. The user picks/enters continent name.
3. The System executes the query.
4. Database returns sorted data set.
5. User views report.

## EXTENSIONS

2. User typed invalid continent name: "Please enter a valid continent name."
3. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

1. Selection option to pick between all capital cities in the world, continent or region.
2. Dropdown list of continents to choose.

## SCHEDULE

**DUE DATE**: Release 2.0
