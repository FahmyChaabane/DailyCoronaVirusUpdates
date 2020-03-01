import React, { Component } from "react";
import Virus from "./virus.png";
import "./App.css";
import axios from "axios";

class App extends Component {
  state = {
    data: {
      list: [],
      num: 0
    }
  };

  async componentDidMount() {
    console.log("App.jsx");
    try {
      const data = (await axios.get("http://localhost:8080/status")).data;
      this.setState({ data });
      console.log("body of the request", data);
    } catch (error) {
      console.log("something went wrong with the called server");
    }
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={Virus} className="App-logo" alt="logo" />
          <p>
            A daily <code>updates</code> on the Corona Virus states over the
            world
          </p>
          <a
            className="App-link"
            href="https://www.who.int/health-topics/coronavirus"
            target="_blank"
            rel="noopener noreferrer"
          >
            learn more...
          </a>
        </header>
        <div className="card text-center">
          <div className="card-body">
            <h5 className="card-title">
              Total number of confirmed effected people
            </h5>
            <b className="card-text">{this.state.data.totateff}</b>
          </div>
          <div className="card-footer text-muted"></div>
        </div>
        <div>
          {this.state.data.list.length === 0 ? (
            <code>Loading data...</code>
          ) : (
            <table className="table container">
              <thead>
                <tr className="bg-info">
                  <th scope="col">Province/State</th>
                  <th scope="col">Country/Region</th>
                  <th scope="col">Number of effected confirmed</th>
                </tr>
              </thead>
              <tbody>
                {this.state.data.list.map((data, index) => (
                  <tr key={index}>
                    <td>
                      {data.state === "" ? (
                        <p style={{ color: "red" }}>Not Applicable</p>
                      ) : (
                        data.state
                      )}
                    </td>
                    <th>{data.country}</th>
                    <td>{data.lastTotalCases}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      </div>
    );
  }
}

export default App;
