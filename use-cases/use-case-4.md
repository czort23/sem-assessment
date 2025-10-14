# USE CASE: 4 Population Report
## CHARACTERISTIC INFORMATION

### Goal in Context

As a Data Analyst for an Organisation, I want to produce a report about population distributions across all world, continents, regions, countries, districts, or cities (including a %) so that I can analyse and compare population sizes, and patterns.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

The system is up and running and connected to database.
The database contains population data.

### Success End Condition

The system displays report for Data Analyst showing:
1. The name of the continent/region/country.
2. The total population of the continent/region/country.
3. The total population of the continent/region/country living in cities (including a %).
4. The total population of the continent/region/country not living in cities (including a %).

### Failed End Condition

No report is produced.
Error message is displayed.

### Primary Actor

Data Analyst.

### Trigger

Data Analyst selects Population Reports from the menu.

## MAIN SUCCESS SCENARIO

1. Data Analyst opens up the System.
2. The Analyst selects Population Reports from option menu.
3. The System displays filtering options for:
   1. World
   2. Continent
   3. Region
   4. District
   5. City
4. The Analyst chooses the option and inputs additional requirements if needed (N value).
5. The System displays the report.
6. The Analyst provides the report for an organisation.

## EXTENSIONS

4. Invalid input or N value:
   1. System displays an error "Please enter a valid value"
5. No results returned:
   1. The System displays "No data found."

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
