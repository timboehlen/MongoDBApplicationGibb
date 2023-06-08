db.createUser(
    {
        user: "myAdmin",
        pwd: "pass",
        roles: [
            {
                role: "readWrite",
                db: "gibb"
            }
        ]
    }
);