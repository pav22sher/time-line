# TimeLine

Input:
| startTime | endTime | name  |
|-----------|---------|-------|
| 10        | 100     | Test1 |
| 50        | 70      | Test2 |
| 60        | 120     | Test3 |
| 150       | 300     | Test4 |

Output:
| startTime | endTime | names               |
|-----------|---------|---------------------|
| 10        | 49      | Test1               |
| 50        | 59      | Test1, Test2        |
| 60        | 70      | Test1, Test2, Test3 |
| 71        | 100     | Test1, Test3        |
| 101       | 120     | Test3               |
| 150       | 300     | Test4               |
