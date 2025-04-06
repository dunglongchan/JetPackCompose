package testing.jetpack.compose.data

import testing.jetpack.compose.domain.model.LocalUser
import testing.jetpack.compose.domain.model.UserInformation
import testing.jetpack.compose.domain.model.UserLoginForm

class LocalUserDataSource {
    private val sampleData = listOf(
        LocalUser(
            userID = 1,
            userInfo = UserInformation(
                fullName = "Nguyễn Văn A",
                dateOfBirth = "1990-01-01",
                email = "nguyenvana@example.com",
                phone = "0901234567",
                jobTitle = "Software Engineer"
            ),
            loginForm = UserLoginForm(
                userName = "nguyenvana",
                password = "Password123!"
            )
        ),
        LocalUser(
            userID = 2,
            userInfo = UserInformation(
                fullName = "Trần Thị B",
                dateOfBirth = "1985-05-20",
                email = "tranb@example.com",
                phone = "0902345678",
                jobTitle = "Product Manager"
            ),
            loginForm = UserLoginForm(
                userName = "tranb",
                password = "Qwerty123@"
            )
        ),
        LocalUser(
            userID = 3,
            userInfo = UserInformation(
                fullName = "Lê Minh C",
                dateOfBirth = "1995-10-10",
                email = "leminhc@example.com",
                phone = "0903456789",
                jobTitle = "Designer"
            ),
            loginForm = UserLoginForm(
                userName = "leminhc",
                password = "Design@123"
            )
        ),
        LocalUser(
            userID = 4,
            userInfo = UserInformation(
                fullName = "Phan Đức D",
                dateOfBirth = "1992-03-15",
                email = "phanducd@example.com",
                phone = "0904567890",
                jobTitle = "HR Manager"
            ),
            loginForm = UserLoginForm(
                userName = "phanducd",
                password = "Duc@1234"
            )
        ),
        LocalUser(
            userID = 5,
            userInfo = UserInformation(
                fullName = "Bùi Hoàng E",
                dateOfBirth = "1988-11-25",
                email = "buihoange@example.com",
                phone = "0905678901",
                jobTitle = "Marketing Specialist"
            ),
            loginForm = UserLoginForm(
                userName = "buihoange",
                password = "Bui@1234"
            )
        ),
        LocalUser(
            userID = 6,
            userInfo = UserInformation(
                fullName = "Nguyễn Minh F",
                dateOfBirth = "1993-06-12",
                email = "nguyenf@example.com",
                phone = "0906789012",
                jobTitle = "Data Scientist"
            ),
            loginForm = UserLoginForm(
                userName = "nguyenf",
                password = "Data@1234"
            )
        ),
        LocalUser(
            userID = 7,
            userInfo = UserInformation(
                fullName = "Võ Thị G",
                dateOfBirth = "1994-08-14",
                email = "vothig@example.com",
                phone = "0907890123",
                jobTitle = "Sales Executive"
            ),
            loginForm = UserLoginForm(
                userName = "vothig",
                password = "Sales@1234"
            )
        ),
        LocalUser(
            userID = 8,
            userInfo = UserInformation(
                fullName = "Đỗ Quang H",
                dateOfBirth = "1987-02-22",
                email = "doquangh@example.com",
                phone = "0908901234",
                jobTitle = "Business Analyst"
            ),
            loginForm = UserLoginForm(
                userName = "doquangh",
                password = "QuangH@1234"
            )
        ),
        LocalUser(
            userID = 9,
            userInfo = UserInformation(
                fullName = "Lê Thanh I",
                dateOfBirth = "1996-09-30",
                email = "lethangi@example.com",
                phone = "0909012345",
                jobTitle = "Quality Assurance"
            ),
            loginForm = UserLoginForm(
                userName = "lethangi",
                password = "QA@1234"
            )
        ),
        LocalUser(
            userID = 10,
            userInfo = UserInformation(
                fullName = "Phạm Minh J",
                dateOfBirth = "1989-04-18",
                email = "phamminhj@example.com",
                phone = "0900123456",
                jobTitle = "Customer Support"
            ),
            loginForm = UserLoginForm(
                userName = "phamminhj",
                password = "Support@1234"
            )
        )
    )

    fun login(loginForm: UserLoginForm): LocalUser? {
        return sampleData.find { it.loginForm == loginForm }
    }

    fun getUserByUserName(userName: String): LocalUser? {
        return sampleData.find { it.loginForm.userName == userName }
    }

    fun getUserInfo(userName: String): UserInformation? {
        return sampleData.find { it.loginForm.userName == userName }?.userInfo
    }

    fun getUserByUserID(userID: Int): LocalUser? {
        return sampleData.find { it.userID == userID }
    }

    fun getUserInfo(userID: Int): UserInformation? {
        return sampleData.find { it.userID == userID }?.userInfo
    }

}