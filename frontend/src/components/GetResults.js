import React from 'react';
import axios from 'axios';
import './GetResults.css';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Card from 'react-bootstrap/Card'

export default class GetResults extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          goals: [],
          finalGoals: {
              firstSent: [],
              remainder: []
          },
          value: ""
        }
    }
  
    spliceString = () => {
      var finalGoals = {
          firstSent: [],
          remainder: []
      };
        var i;
        for (i = 0; i < this.state.goals.length; i++) {
            var firstPeroid = this.state.goals[i].indexOf('.');
            finalGoals.firstSent.push(this.state.goals[i].slice(0,firstPeroid + 1));
            finalGoals.remainder.push(this.state.goals[i].slice(firstPeroid + 1));
        }
        this.setState({finalGoals});
    }
  
    handleSubmit = (event) => {
      event.preventDefault();
      let userSearch = this.state.value;
      if (userSearch === "") {
          alert("Please do not enter an empty field!");
      }
      else {
          axios.get(`http://localhost:8080/parser`, {params: {userSearch}})
          .then(res => {
              const goals = res.data;
              this.setState({ goals }, this.spliceString);
          })
      }
    }
  
    handleChange = (event) => {
      this.setState({value: event.target.value});
  }
  



  render() {
    return (
        <div className='goal'>
            
          <Form onSubmit={ this.handleSubmit }>
              <Form.Group controlId="formBasicSearch">
                  <Form.Label id='form-label'>Enter A Goal</Form.Label>
                  <Form.Control type="text" placeholder="'Eat healthier'" id ='input' value= {this.state.value} onChange={this.handleChange} />
              </Form.Group>
              <Button variant="dark" as="input" type="submit" value="Submit" />{' '}
          </Form>
            <div class='row'>
                {this.state.finalGoals.firstSent.map((fs, index) =>
                    <Card 
                        style={{ width: '18rem' }} 
                        bg='info' 
                        text='white'>
                        <Card.Body>
                            <Card.Title>{fs}</Card.Title>
                            <Card.Text>{this.state.finalGoals.remainder[index]}</Card.Text>
                        </Card.Body>
                    </Card>)}       
            </div>
      </div>
    );
  }
}