@template.layout.page(
    content = @`
        <h3>Create user</h3>
        <form action="/users" method="post">
          <div>
            <label>
              firstName
              <input type="text" required name="firstName" />
            </label>
          </div>
          <div>
            <label>
              lastName
              <input type="text" required name="lastName" />
            </label>
          </div>
          <div>
            <label>
              email
              <input type="email" required name="email" />
            </label>
          </div>
          <div>
            <label>
              password
              <input type="password" required name="password" />
            </label>
          </div>
          <div>
            <label>
              confirm password
              <input type="password" required name="passwordConfirmation" />
            </label>
          </div>
          <input type="submit" value="Create" />
        </form>
    `
)
