const axios = require('axios');
let url;
let username;
let password;

beforeAll(async function() { 
  console.log("Toilet API Suite has started")
  const response = await axios.get('http://ptsv2.com/t/fu807-1554722621/post');
  username = response.data.username;
  password = response.data.password;
  url = response.data.targetUrl;
});

async function makeCallToApi(user, passwd) {
  const response = await axios.post(url, {}, {
    auth: {
      username: user,
      password: passwd
    }
  }).catch(function(error) {
    return error;
  });
  return response;
}

describe('Toilet API Suite', function() {
  it('API call should return 401 code when no username and password provided', async function() {
      const error = await makeCallToApi('', '');
      expect(error.response.status).toEqual(401);
      console.log("Status code is 401 when crediantials are incorrect Verification Passed")
  });

  it('API call should return 200 code when username and password are provided', async function() {
    const response = await makeCallToApi(username, password);
    expect(response.status).toEqual(200);
    console.log("Status code is 200 when crediantials are correct Verification Passed")
  });

  it('API call returns a valid IP address', async function() {
    const response = await makeCallToApi(username, password);
    expect(require('net').isIPv4(response.data.ip)).toBeTrue();
    console.log("The Call returned a valid IP address verification Passed")
  });

  it('API call returned token that has 10 symbols', async function() {
    const response = await makeCallToApi(username, password);
    expect(response.data.token.length).toEqual(10);
    console.log("The Call returned token with 10 symbols verification passed")
  });
});

afterAll(function() { 
  console.log("Toilet API Suite has finished")
});  