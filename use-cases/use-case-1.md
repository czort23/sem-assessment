# USE CASE: 1 Produce a Report on the Salary of Employees of a Given Role

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *data analyst* I want *to view and sort all countries by population, world, continent, religion or top N, * so that *I can analyse how populations are distributed globally and regionally.*

### Scope

Organisation.

### Level

User goal.

### Preconditions

Database connection is active and valid country data is present.

### Success End Condition

Country report displayed in descending order by population.

### Failed End Condition

If query fails or data unavailable error is displayed.

### Primary Actor

Data Analyst.

### Trigger

"Country Report" is selected.

## MAIN SUCCESS SCENARIO

1. User selects "Country Report".
2. System shows for filter.
3. HR advisor extracts current salary information of all employees of the given role.
4. HR advisor provides report to finance.

## EXTENSIONS

3. **Role does not exist**:
    1. HR advisor informs finance no role exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
