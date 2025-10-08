# USE CASE: 22 The top N populated capital cities in a region where N is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to view a report of all the top N most populated capital cities within a selected region, where N is provided by the user.

### Scope

World Population Reporting System

### Level

Primary task.

### Preconditions

Database connection is active.
Database contains valid city, country records with correct links between country and capital city, as well as region association.
The user provides a region name.
The user provides a valid number N.

### Success End Condition

System generates report and displays a list of top N capital cities within the selected region, ordered from largest to the smallest population.

### Failed End Condition

User enters invalid region name.
User enters invalid N.
If query fails or data unavailable error is displayed.

### Primary Actor

Organisation user.

### Trigger

"City Reports --> Top N Capital Cities by Region" is selected.
User specifies the region.
User specifies value N.

## MAIN SUCCESS SCENARIO

1. User selects navigates to City Reports --> Top N Capital Cities by Region.
2. The user picks/selects a region name.
3. The user picks/selects N value.
4. The System executes the query.
5. Database returns sorted data set.
6. User views report.

## EXTENSIONS

2. User typed invalid region name: "Please enter a valid region name."
3. User typed invalid N value: "Please enter a valid number."
4. Database connection fails: "Database unavailable, try again."

## SUB-VARIATIONS

1. List for N values.
2. Provide filters for continent, region or all.

## SCHEDULE

**DUE DATE**: Release 1.0
